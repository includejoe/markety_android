package org.includejoe.markety.feature_authentication.util

sealed class ValidationEvent {
    object Success: ValidationEvent()
}
