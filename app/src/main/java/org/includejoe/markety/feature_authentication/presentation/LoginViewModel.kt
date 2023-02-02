package org.includejoe.markety.feature_authentication.presentation


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.includejoe.markety.R
import org.includejoe.markety.base.domain.AppState
import org.includejoe.markety.base.domain.repository.UserPreferencesRepository
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_authentication.domain.use_case.AuthenticationUseCases
import org.includejoe.markety.feature_authentication.util.*
import org.includejoe.markety.feature_authentication.util.validators.FormValidators
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthenticationUseCases,
    private val validators: FormValidators,
    private val tokenManager: TokenManager,
    private val appState: State<AppState>,
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
                        _state.value = LoginState(data = result.data, submissionSuccess = true)
                        tokenManager.login(result.data?.jwt)
                        setLoggedInUser(result.data?.username!!)
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

    private fun setLoggedInUser(username: String) {
        viewModelScope.launch {
            userPreferencesRepository.setLoggedInUser(username)
            appState.value.loggedInUser = username
        }
    }
}