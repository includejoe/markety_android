package org.includejoe.markety.feature_authentication.util.validators

import org.includejoe.markety.R
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.feature_authentication.util.ValidationResult

class ValidateUsername {
    operator fun invoke(
        username: String,
        type: String = Constants.REGISTER_VALIDATE
    ): ValidationResult {

        if(username.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.blank_username
            )
        }

        when(type) {
            Constants.REGISTER_VALIDATE -> {
                if(username.length < 3){
                    return ValidationResult(
                        successful = false,
                        errorMessage = R.string.min_username
                    )
                }

                val isValid = username.matches(Constants.USERNAME_REGEX.toRegex())
                if(!isValid) {
                    return ValidationResult(
                        successful = false,
                        errorMessage = R.string.invalid_username
                    )
                }


            }
        }

        return ValidationResult(successful = true)
    }
}