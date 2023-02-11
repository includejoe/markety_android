package org.includejoe.markety.feature_user.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.includejoe.markety.R
import org.includejoe.markety.base.BaseApplication
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_user.data.remote.dto.toUser
import org.includejoe.markety.feature_user.domain.use_case.UserUseCases
import org.includejoe.markety.feature_user.util.UserState
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    val baseApp: BaseApplication,
    private val userUseCases: UserUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(UserState())
    val state: State<UserState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_USERNAME)?.let { username ->
            getUser(username)
            getUserPosts(username)
        }
    }

    fun getUser(username: String) {
        viewModelScope.launch {
            userUseCases.getUser(
                jwt = tokenManager.readToken(),
                username = username
            ).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            loading = true
                        )
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            data = result.data?.toUser(),
                            success = true,
                            loading = false,
                            error = null
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            error = result.message ?: R.string.unexpected_error,
                            loading = false,
                            success = false,
                            data = null
                        )
                    }
                }
            }
        }
    }

    private fun getUserPosts(username: String) {
        viewModelScope.launch {
            userUseCases.getUserPosts(
                jwt = tokenManager.readToken(),
                username = username
            ).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            userPostsLoading = true
                        )
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            userPostsLoading = false,
                            userPostsError = null,
                            userPosts = result.data
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            userPostsLoading = false,
                            userPostsError = result.message ?: R.string.unexpected_error,
                            userPosts = null
                        )
                    }
                }
            }
        }
    }
}