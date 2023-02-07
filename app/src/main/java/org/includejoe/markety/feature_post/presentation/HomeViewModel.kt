package org.includejoe.markety.feature_post.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.includejoe.markety.base.BaseApplication
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_post.domain.use_case.PostUseCases
import org.includejoe.markety.feature_post.util.HomeViewModelState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val tokenManager: TokenManager,
    val baseApp: BaseApplication
): ViewModel() {
    private val _state = mutableStateOf(HomeViewModelState())
    val state: State<HomeViewModelState> = _state

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            postUseCases.getPosts(tokenManager.readToken()).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            postsLoading = true
                        )
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            postsLoading = false,
                            getPostsError = null,
                            posts = result.data
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            postsLoading = false,
                            getPostsError = result.message,
                            posts = null
                        )
                    }
                }
            }
        }
    }
}