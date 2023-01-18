package org.includejoe.markety.feature_authentication.util

sealed class LoginEvent {
    data class UsernameChanged(val username: String): LoginEvent()
    data class PasswordChanged(val password: String): LoginEvent()

    object Submit: LoginEvent()
}