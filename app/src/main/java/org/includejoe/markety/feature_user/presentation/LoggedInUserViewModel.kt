package org.includejoe.markety.feature_user.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.includejoe.markety.R
import org.includejoe.markety.base.BaseApplication
import org.includejoe.markety.base.domain.repository.UserPreferencesRepository
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_user.data.remote.dto.toUser
import org.includejoe.markety.feature_user.domain.use_case.UserUseCases
import org.includejoe.markety.feature_user.util.UserViewModelState
import javax.inject.Inject

// TODO: Create viewModel for user and logged in usser

@HiltViewModel
class LoggedInUserViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    val baseApp: BaseApplication,
    private val userUseCases: UserUseCases,
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {
    private val _state = mutableStateOf(UserViewModelState())
    val state: State<UserViewModelState> = _state

    init {
        getLoggedInUser()
        getLoggedInUsername()

    }

    fun getLoggedInUser() {
        viewModelScope.launch {
            userUseCases.getLoggedInUserUseCase(tokenManager.readToken()).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            data = result.data?.toUser(),
                            getLoggedInUserSuccess = true,
                            isLoading = false,
                            getUserLoggedInError = null
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            getUserLoggedInError = result.message ?: R.string.unexpected_error,
                            isLoading = false,
                            getLoggedInUserSuccess = false,
                            data = null
                        )
                    }
                }
            }
        }
    }
    private fun getLoggedInUsername() {
        viewModelScope.launch {
            val username = userPreferencesRepository.getLoggedInUser()
            baseApp.loggedInUser.value = username
        }
    }
}