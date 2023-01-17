package org.includejoe.markety.feature_authentication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterDTO(
    @SerializedName("bus_category")
    val busCategory: String,
    @SerializedName("bus_name")
    val busName: String,
    val dob: String,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val gender: String,
    @SerializedName("is_vendor")
    val isVendor: Boolean,
    @SerializedName("last_name")
    val lastName: String,
    val location: String,
    val phone: String,
    val username: String
)