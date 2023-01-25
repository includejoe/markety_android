package org.includejoe.markety.feature_authentication.util.validators

import org.includejoe.markety.R
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.feature_authentication.util.ValidationResult

class ValidateGender {
    operator fun invoke(
        gender: String,
    ): ValidationResult {

        if(gender.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.blank_gender
            )
        }

        return ValidationResult(successful = true)
    }
}