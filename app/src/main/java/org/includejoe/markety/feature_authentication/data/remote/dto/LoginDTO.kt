package org.includejoe.markety.feature_authentication.data.remote.dto

import com.google.gson.annotations.SerializedName
import org.includejoe.markety.feature_authentication.domain.model.Login

data class LoginDTO(
    val email: String,
    @SerializedName("is_staff")
    val isStaff: Boolean,
    val tokens: Tokens,
    val username: String
)

fun LoginDTO.toLogin(): Login {
    return Login (
        username = username,
        email = email,
        isStaff = isStaff,
        tokens = tokens,
    )
}