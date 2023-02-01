package org.includejoe.markety.feature_user.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.includejoe.markety.R
import org.includejoe.markety.base.domain.AppState
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_user.data.remote.dto.toUser
import org.includejoe.markety.feature_user.domain.model.User
import org.includejoe.markety.feature_user.domain.use_case.UserUseCases
import org.includejoe.markety.feature_user.util.UserViewModelState
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val appState: State<AppState>,
    private val userUseCases: UserUseCases,
    private val userDataStore: DataStore<User>
): ViewModel() {
    private val _state = mutableStateOf(UserViewModelState())
    val state: State<UserViewModelState> = _state

    init {
        getLoggedInUser()
    }

    fun logOut() {
        tokenManager.logOut()
    }

    fun toggleTheme() {
        appState.value.isDarkTheme = !appState.value.isDarkTheme
    }

    private fun getLoggedInUser() {
        viewModelScope.launch {
            userUseCases.getLoggedInUserUseCase(tokenManager.readToken()).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }

                    is Response.Success -> {
//                        userDataStore.updateData {
//                            result.data?.toUser()!!
//                        }
                        _state.value = _state.value.copy(
                            data = result.data?.toUser(),
                            getLoggedInUserSuccess = true,
                            getUserLoggedInError = null
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            getUserLoggedInError = result.message ?: R.string.unexpected_error,
                            getLoggedInUserSuccess = false,
                            data = null
                        )
                    }
                }
            }
        }
    }
}