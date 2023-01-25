package org.includejoe.markety.feature_authentication.util

import org.includejoe.markety.feature_authentication.data.remote.dto.LoginDTO

data class LoginState(
    var username: String = "",
    val usernameError: Int? = null,

    var password: String = "",
    val passwordError: Int? = null,

    val isSubmitting: Boolean = false,
    val submissionError: Any? = null,
    var submissionSuccess: Boolean = false,

    val data: LoginDTO? = null,
)
