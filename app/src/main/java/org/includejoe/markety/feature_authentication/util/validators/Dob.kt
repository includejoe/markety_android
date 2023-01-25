package org.includejoe.markety.feature_authentication.util.validators

import org.includejoe.markety.R
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.feature_authentication.util.ValidationResult

class ValidateDob {
    operator fun invoke(
        dob: String,
    ): ValidationResult {

        if(dob.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.blank_dob
            )
        }

        return ValidationResult(successful = true)
    }
}