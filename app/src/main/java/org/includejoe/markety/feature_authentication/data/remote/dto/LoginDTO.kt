package org.includejoe.markety.feature_authentication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginDTO(
    val email: String,
    @SerializedName("is_staff")
    val isStaff: Boolean,
    val tokens: TokensDTO,
    val username: String
)