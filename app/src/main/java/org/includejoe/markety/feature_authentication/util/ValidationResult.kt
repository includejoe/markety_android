package org.includejoe.markety.feature_authentication.util

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: Int? = null
)