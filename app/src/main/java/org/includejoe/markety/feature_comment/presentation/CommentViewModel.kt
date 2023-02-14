package org.includejoe.markety.feature_comment.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_comment.domain.use_case.CommentUseCases
import org.includejoe.markety.feature_comment.util.CommentState
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentUseCases: CommentUseCases,
    private val tokenManager: TokenManager
): ViewModel() {
    private val _state = mutableStateOf(CommentState())
    val state: State<CommentState> = _state

    fun createComment(
        postId: String,
        body: String
    ) {
        viewModelScope.launch {
            commentUseCases.createComment(
                jwt = tokenManager.readToken(),
                postId = postId,
                body = body
            ).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            loading = true
                        )
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            loading = false,
                            data = result.data
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            loading = false,
                            data = null
                        )
                    }
                }
            }
        }
    }

    fun replyComment(
        postId: String,
        commentId: String,
        body: String
    ) {
        viewModelScope.launch {
            commentUseCases.replyComment(
                jwt = tokenManager.readToken(),
                commentId = commentId,
                postId = postId,
                body = body
            ).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            loading = true
                        )
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            loading = false,
                            data = result.data
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            loading = false,
                            data = null
                        )
                    }
                }
            }
        }
    }

    fun likeComment(
        commentId: String
    ) {
        viewModelScope.launch {
            commentUseCases.likeComment(
                jwt = tokenManager.readToken(),
                commentId = commentId
            ).collect()
        }
    }
}