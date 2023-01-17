package org.includejoe.markety.feature_authentication.domain.model

data class Register(
    val busCategory: String,
    val busName: String,
    val dob: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val isVendor: Boolean,
    val lastName: String,
    val location: String,
    val password: String,
    val phone: String,
    val username: String
)
