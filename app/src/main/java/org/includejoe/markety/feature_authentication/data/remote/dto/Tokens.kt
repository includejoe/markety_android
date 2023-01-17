package org.includejoe.markety.feature_authentication.data.remote.dto

data class Tokens(
    val access: String,
    val refresh: String
)