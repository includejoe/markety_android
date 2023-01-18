package org.includejoe.markety.feature_authentication.util

import org.includejoe.markety.feature_authentication.domain.model.Login

data class LoginState(
    val username: String = "",
    val usernameError: Any? = null,
    val password: String = "",
    val passwordError: Any? = null,
    val isSubmitting: Boolean = false,
    val submissionError: Any? = null,
    val data: Login? = null,
)
