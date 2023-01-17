package org.includejoe.markety.feature_authentication.util

sealed class FormEvent {
    data class EmailChanged(val email: String): FormEvent()
    data class UsernameChanged(val username: String): FormEvent()
    data class PasswordChanged(val password: String): FormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String): FormEvent()

    object Submit: FormEvent()
}