package org.includejoe.markety.feature_authentication.util.validators

import org.includejoe.markety.R
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.feature_authentication.util.ValidationResult

class ValidatePhone {
    operator fun invoke(
        phone: String,
    ): ValidationResult {

        if(phone.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.blank_phone
            )
        }

        return ValidationResult(successful = true)
    }
}