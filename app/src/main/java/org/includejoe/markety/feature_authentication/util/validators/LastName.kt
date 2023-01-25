package org.includejoe.markety.feature_authentication.util.validators

import org.includejoe.markety.R
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.feature_authentication.util.ValidationResult

class ValidateLastName {
    operator fun invoke(
        lastName: String,
    ): ValidationResult {

        if(lastName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.blank_lastname
            )
        }

        return ValidationResult(successful = true)
    }
}