package org.includejoe.markety.feature_authentication.util

import org.includejoe.markety.base.data.remote.dto.GooglePredictionsDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.RegisterDTO

data class RegisterState(
    var username: String = "",
    val usernameError: Int? = null,

    var isUsernameAvailable: Boolean? = false,
    var checkingUsername: Boolean = false,

    var email: String = "",
    val emailError: Int? = null,

    var password: String = "",
    val passwordError: Int? = null,

    var confirmPassword: String = "",
    val confirmPasswordError: Int? = null,

    var firstName: String = "",
    val firstNameError: Int? = null,

    var lastName: String = "",
    val lastNameError: Int? = null,

    var phone: String = "",
    var phoneCountryCode: String = "",

    val phoneError: Int? = null,

    var gender: String = "",
    val genderError: Int? = null,

    var location: String = "",
    val locationError: Int? = null,

    var dob: String = "",
    val dobError: Int? = null,

    var isVendor: Boolean = false,
    val isVendorError: Int? = null,

    var busName: String = "",
    val busNameError: Int? = null,

    var busCategory: String = "",
    val busCategoryError: Int? = null,

    var googlePlacesPredictions: GooglePredictionsDTO? = null,
    val isGooglePlacesPredictionsLoading: Boolean = false,
    val googlePlacesPredictionsError: Int? = null,

    val isSubmitting: Boolean = false,
    val submissionError: Any? = null,
    var submissionSuccess: Boolean = false,

    val data: RegisterDTO? = null,

    var currentDisplay: Int = 1
)