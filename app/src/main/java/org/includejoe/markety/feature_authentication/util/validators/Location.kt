package org.includejoe.markety.feature_authentication.util.validators

import org.includejoe.markety.R
import org.includejoe.markety.feature_authentication.util.ValidationResult

class ValidateLocation {
    operator fun invoke(
        location: String,
    ): ValidationResult {

        if(location.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.blank_location
            )
        }

        return ValidationResult(successful = true)
    }
}