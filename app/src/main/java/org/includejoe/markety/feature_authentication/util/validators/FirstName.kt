package org.includejoe.markety.feature_authentication.util.validators

import org.includejoe.markety.R
import org.includejoe.markety.feature_authentication.util.ValidationResult

class ValidateFirstName {
    operator fun invoke(
        firstName: String,
    ): ValidationResult {

        if(firstName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.blank_firstname
            )
        }

        return ValidationResult(successful = true)
    }
}