package org.includejoe.markety.feature_authentication.presentation

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
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_authentication.domain.use_case.AuthenticationUseCases
import org.includejoe.markety.feature_authentication.util.*
import org.includejoe.markety.base.util.validators.FormValidators
import org.includejoe.markety.feature_user.data.remote.dto.toUser
import org.includejoe.markety.feature_user.domain.use_case.GetLoggedInUserUseCase
import org.includejoe.markety.feature_user.domain.use_case.GetUserUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthenticationUseCases,
    private val loggedInUserUseCase: GetLoggedInUserUseCase,
    private val validators: FormValidators,
    private val tokenManager: TokenManager,
    private val baseApp: BaseApplication,
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun onEvent(event: FormEvent) {
        when(event) {
            is FormEvent.UsernameChanged -> {
                _state.value = _state.value.copy(username = event.username)
            }

            is FormEvent.PasswordChanged -> {
                _state.value = _state.value.copy(password = event.password)
            }

            is FormEvent.Login -> {
                submit()
            }
            else -> {}
        }
    }

    private fun submit() {
        val usernameResult = validators.username(_state.value.username, type = Constants.LOGIN_VALIDATE)
        val passwordResult = validators.password(_state.value.password, type = Constants.LOGIN_VALIDATE)

        val hasError = listOf(
            usernameResult,
            passwordResult
        ).any{ !it.successful }

        if(hasError) {
            _state.value = _state.value.copy(
                usernameError = usernameResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            authUseCases.login(
                username = _state.value.username,
                password = _state.value.password
            ).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = LoginState(isSubmitting = true)
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            data = result.data
                        )
                        tokenManager.login(result.data?.jwt)
                        getUserDetails(result.data?.jwt)
                    }

                    is Response.Error -> {
                        _state.value = LoginState(
                            submissionError =  result.message ?: R.string.unexpected_error
                        )
                    }
                }
            }
        }
    }

    private fun getUserDetails(jwt: String?) {
        viewModelScope.launch {
            loggedInUserUseCase(jwt).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {}

                    is Response.Success -> {
                        userPreferencesRepository.setUserDetails(result.data?.toUser()!!)
                        baseApp.userDetails.value  = result.data.toUser()
                        _state.value = _state.value.copy(
                            isSubmitting = false,
                            submissionSuccess = true
                        )
                    }

                    is Response.Error -> {
                        _state.value = LoginState(
                            submissionError =  result.message ?: R.string.unexpected_error
                        )
                    }
                }
            }
        }
    }
}