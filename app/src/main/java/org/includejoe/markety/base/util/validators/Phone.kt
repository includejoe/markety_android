package org.includejoe.markety.base.util.validators

import org.includejoe.markety.R
import org.includejoe.markety.feature_authentication.util.ValidationResult

class ValidatePhone {
    operator fun invoke(
        phone: String,
    ): ValidationResult {
        val notValidPhone = phone.any(Char::isLetter)

        if(phone.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.blank_phone
            )
        }

        if(phone.length < 5) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.invalid_phone
            )
        }

        if(notValidPhone) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.invalid_phone
            )
        }

        return ValidationResult(successful = true)
    }
}