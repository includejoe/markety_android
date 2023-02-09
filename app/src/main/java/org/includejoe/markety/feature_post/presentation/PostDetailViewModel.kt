package org.includejoe.markety.feature_post.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.includejoe.markety.base.BaseApplication
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_comment.domain.use_case.CommentUseCases
import org.includejoe.markety.feature_comment.domain.use_case.GetPostCommentsUseCase
import org.includejoe.markety.feature_post.domain.use_case.PostUseCases
import org.includejoe.markety.feature_post.util.PostDetailVMState
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val commentUseCases: CommentUseCases,
    private val tokenManager: TokenManager,
    val baseApp: BaseApplication,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(PostDetailVMState())
    val state: State<PostDetailVMState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_POST_ID)?.let { postId ->
            getPost(postId)
            getPostComments(postId)
        }
    }

    private fun getPostComments(postId: String) {
        viewModelScope.launch {
            commentUseCases.getPostComments(
                jwt = tokenManager.readToken(),
                postId = postId
            ).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            commentsLoading = true
                        )
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            commentsLoading = false,
                            comments = result.data,
                            commentError = null
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            commentsLoading = false,
                            comments = null,
                            commentError = result.message
                        )
                    }
                }
            }
        }
    }

    private fun getPost(postId: String) {
        viewModelScope.launch {
            postUseCases.getPost(
                postId = postId,
                jwt = tokenManager.readToken()
            ).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            post = result.data,
                            error = null
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            post = null,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}