package org.includejoe.markety.base.util.validators

import org.includejoe.markety.R
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.feature_authentication.util.ValidationResult

class ValidatePassword {
    operator fun invoke(
        password: String,
        type: String = Constants.REGISTER_VALIDATE
    ): ValidationResult {
        when(type) {
            Constants.REGISTER_VALIDATE -> {
                if(password.length < 8) {
                    return ValidationResult(
                        successful = false,
                        errorMessage = R.string.min_password
                    )
                }
                val containsLettersAndDigits = password.any { it.isDigit() } && password.any {it.isLetter()}
                if(!containsLettersAndDigits) {
                    return ValidationResult(
                        successful = false,
                        errorMessage = R.string.invalid_password
                    )
                }
            }

            Constants.LOGIN_VALIDATE -> {
                if(password.isBlank()) {
                    return ValidationResult(
                        successful = false,
                        errorMessage = R.string.blank_password
                    )
                }
            }
        }

        return ValidationResult(
            successful = true
        )
    }
}