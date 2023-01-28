package org.includejoe.markety.feature_authentication.domain.model

data class RegistrationError(
    val email: List<String>? = null,
    val first_name: List<String>? = null,
    val last_name: List<String>? = null,
    val password: List<String>? = null,
    val dob: List<String>? = null,
    val gender: List<String>? = null,
    val username: List<String>? = null,
)