package org.includejoe.markety.feature_authentication.domain.model

import org.includejoe.markety.feature_authentication.data.remote.dto.TokensDTO

data class Login(
    val email: String = "",
    val username: String = "",
    val password: String? = null,
    val tokens: TokensDTO? = null,
    val isStaff: Boolean = false
)
