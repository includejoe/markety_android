package org.includejoe.markety.feature_authentication.data.remote.dto

data class LoginDTO(
    val username: String,
    val jwt: String,
)