package org.includejoe.markety.feature_authentication.util

data class FormState(
    val email: String = "",
    val emailError: String? = null,
    val username: String = "",
    val usernameError: String = "",
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
)
