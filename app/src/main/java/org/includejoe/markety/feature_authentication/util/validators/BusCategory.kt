package org.includejoe.markety.feature_authentication.util.validators

import org.includejoe.markety.R
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.feature_authentication.util.ValidationResult

class ValidateBusCategory {
    operator fun invoke(
        busCategory: String,
    ): ValidationResult {

        if(busCategory.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.blank_bus_category
            )
        }

        return ValidationResult(successful = true)
    }
}