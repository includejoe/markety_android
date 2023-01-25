package org.includejoe.markety.feature_authentication.util

sealed class FormEvent {
    data class UsernameChanged(val username: String): FormEvent()
    data class PasswordChanged(val password: String): FormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String): FormEvent()
    data class FirstNameChanged(val firstName: String): FormEvent()
    data class LastNameChanged(val lastName: String): FormEvent()
    data class EmailChanged(val email: String): FormEvent()
    data class PhoneChanged(val phone: String): FormEvent()
    data class LocationChanged(val location: String): FormEvent()
    data class BusNameChanged(val busName: String): FormEvent()
    data class BusCategoryChanged(val busCategory: String): FormEvent()
    data class DobChanged(val dob: String): FormEvent()

    object Next: FormEvent()
    object Previous: FormEvent()
    object Submit: FormEvent()
}