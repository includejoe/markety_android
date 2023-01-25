package org.includejoe.markety.feature_authentication.util.validators

import android.util.Patterns
import org.includejoe.markety.R
import org.includejoe.markety.feature_authentication.util.ValidationResult

class ValidateEmail {
    operator fun invoke(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.blank_email
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.invalid_email
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}