package org.includejoe.markety.base.util.validators

import org.includejoe.markety.R
import org.includejoe.markety.feature_authentication.util.ValidationResult

class ValidateConfirmPassword {
    operator fun invoke(password: String, confirmPassword: String): ValidationResult {
        if(password != confirmPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.match_passwords
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}