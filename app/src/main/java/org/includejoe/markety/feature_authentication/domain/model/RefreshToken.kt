package org.includejoe.markety.feature_authentication.domain.model

data class RefreshTokenRequest(
    val refresh: String
)

data class RefreshTokenResponse(
    val access: String
)
