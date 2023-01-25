package org.includejoe.markety.feature_authentication.domain.model

data class Register(
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val gender: String,
    val dob: String,
    val location: String,
    val isVendor: Boolean,
    val busCategory: String,
    val busName: String,
    val password: String,
)
