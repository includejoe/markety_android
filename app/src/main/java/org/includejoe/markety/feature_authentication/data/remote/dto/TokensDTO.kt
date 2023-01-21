package org.includejoe.markety.feature_authentication.data.remote.dto

data class TokensDTO(
    val access: String,
    val refresh: String
)