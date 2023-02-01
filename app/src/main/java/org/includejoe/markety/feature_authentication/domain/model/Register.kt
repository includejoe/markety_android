package org.includejoe.markety.feature_authentication.domain.model

import com.google.gson.annotations.SerializedName

data class Register(
    val email: String,
    val username: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val password: String,
    val phone: String,
    val gender: String,
    val dob: String,
    val location: String,
    @SerializedName("is_vendor")
    val isVendor: Boolean,
    @SerializedName("bus_category")
    val busCategory: String?,
    @SerializedName("bus_name")
    val busName: String?,
)
