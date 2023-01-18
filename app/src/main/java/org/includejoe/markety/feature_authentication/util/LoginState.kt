package org.includejoe.markety.feature_authentication.util

import org.includejoe.markety.feature_authentication.domain.model.Login

data class LoginState(
    var username: String = "",
    val usernameError: Int? = null,
    var password: String = "",
    val passwordError: Int? = null,
    val isSubmitting: Boolean = false,
    val submissionError: Int? = null,
    val data: Login? = null,
)
