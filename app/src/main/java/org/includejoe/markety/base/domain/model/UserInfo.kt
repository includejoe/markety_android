package org.includejoe.markety.base.domain.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val username: String,
    @SerializedName("bus_name")
    val busName: String?,
    @SerializedName("is_vendor")
    val isVendor: Boolean,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("profile_image")
    val profileImage: String?
)