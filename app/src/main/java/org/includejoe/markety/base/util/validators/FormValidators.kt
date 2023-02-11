package org.includejoe.markety.base.util.validators

data class FormValidators(
    val email: ValidateEmail,
    val username: ValidateUsername,
    val firstName: ValidateFirstName,
    val lastName: ValidateLastName,
    val gender: ValidateGender,
    val dob: ValidateDob,
    val phone: ValidatePhone,
    val password: ValidatePassword,
    val confirmPassword: ValidateConfirmPassword,
    val location: ValidateLocation,
    val busName: ValidateBusName,
    val busCategory: ValidateBusCategory
)
