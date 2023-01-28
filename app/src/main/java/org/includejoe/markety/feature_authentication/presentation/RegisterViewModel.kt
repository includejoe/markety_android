package org.includejoe.markety.feature_authentication.presentation

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.includejoe.markety.base.util.Constants
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.includejoe.markety.R
import org.includejoe.markety.base.domain.use_cases.GetGooglePlacesPredictions
import org.includejoe.markety.base.util.Country
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.getCountriesList
import org.includejoe.markety.feature_authentication.domain.use_case.AuthenticationUseCases
import org.includejoe.markety.feature_authentication.util.FormEvent
import org.includejoe.markety.feature_authentication.util.RegisterState
import org.includejoe.markety.feature_authentication.util.validators.FormValidators
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCases: AuthenticationUseCases,
    private val getGooglePlacesUseCase: GetGooglePlacesPredictions,
    private val validators: FormValidators,
): ViewModel() {
    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state
    val countriesList = getCountriesList()
    var currentCountryPhone by mutableStateOf<Country?>(Country("gh", "233", "Ghana"))

    fun onEvent(event: FormEvent) {
        when(event) {
            is FormEvent.UsernameChanged -> {
                _state.value = _state.value.copy(username = event.username)
                checkUsername()
            }

            is FormEvent.PasswordChanged -> {
                _state.value = _state.value.copy(password = event.password)
            }

            is FormEvent.ConfirmPasswordChanged -> {
                _state.value = _state.value.copy(confirmPassword = event.confirmPassword)
            }

            is FormEvent.DobChanged -> {
                _state.value = _state.value.copy(dob = event.dob)
            }

            is FormEvent.GenderChanged -> {
                _state.value = _state.value.copy(gender = event.gender)
            }

            is FormEvent.EmailChanged -> {
                _state.value = _state.value.copy(email = event.email)
            }

            is FormEvent.FirstNameChanged -> {
                _state.value = _state.value.copy(firstName = event.firstName)
            }

            is FormEvent.LastNameChanged -> {
                _state.value = _state.value.copy(lastName = event.lastName)
            }

            is FormEvent.PhoneChanged -> {
                _state.value = _state.value.copy(phone = event.phone)
            }

            is FormEvent.LocationChanged -> {
                _state.value = _state.value.copy(location = event.location)
                getPlacesPredictions()
            }

            is FormEvent.IsVendorChanged -> {
                _state.value = _state.value.copy(isVendor = event.isVendor)
            }

            is FormEvent.BusCategoryChanged -> {
                _state.value = _state.value.copy(busCategory = event.busCategory)
            }

            is FormEvent.BusNameChanged -> {
                _state.value = _state.value.copy(busName = event.busName)
            }

            is FormEvent.Next -> {
                next(currentFieldSet = _state.value.currentDisplay)
            }

            is FormEvent.Previous -> {
                _state.value = _state.value.copy(currentDisplay = _state.value.currentDisplay - 1)
            }

            is FormEvent.Register -> {
                submit()
            }
            else -> {}
        }
    }

    private fun checkUsername() {
        viewModelScope.launch {
            authUseCases.checkUsername(_state.value.username).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            checkingUsername = true
                        )
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            isUsernameAvailable = result.data?.available,
                            usernameError = null,
                            checkingUsername = false
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            isUsernameAvailable = false,
                            checkingUsername = false,
                            usernameError = R.string.unavailable_username
                        )
                    }
                }
            }
        }
    }

    private fun getPlacesPredictions() {
        viewModelScope.launch {
            getGooglePlacesUseCase(input = _state.value.location).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = _state.value.copy(
                            isGooglePlacesPredictionsLoading = true
                        )
                    }

                    is Response.Success -> {
                        _state.value = _state.value.copy(
                            googlePlacesPredictions = result.data,
                            isGooglePlacesPredictionsLoading = false
                        )
                    }

                    is Response.Error -> {
                        _state.value = _state.value.copy(
                            googlePlacesPredictionsError = R.string.something_wrong,
                            isGooglePlacesPredictionsLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun submit() {
        Log.d("register", "registering...")
        if(_state.value.isVendor) {
            val busNameResult = validators.busName(_state.value.busName)
            val busCategoryResult = validators.busCategory(_state.value.busCategory)

            val hasError = listOf(
                busNameResult,
                busCategoryResult
            ).any { !it.successful }

            if(hasError) {
                _state.value = _state.value.copy(
                    busNameError = busNameResult.errorMessage,
                    busCategoryError = busCategoryResult.errorMessage,
                )
                return
            }
        } else {
            val dobResult = validators.dob(_state.value.dob)
            val genderResult = validators.gender(_state.value.gender)

            val hasError = listOf(
                dobResult,
                genderResult
            ).any { !it.successful }

            if(hasError) {
                _state.value = _state.value.copy(
                    dobError = dobResult.errorMessage,
                    genderError = genderResult.errorMessage
                )
                return
            }

            // set busName and busCategory to empty strings if user is signing up as a no vendor
            _state.value = _state.value.copy(
                busName = "",
                busCategory = ""
            )
        }

//        Log.d("submit_data", _state.value.toString())

        viewModelScope.launch {
            authUseCases.register(
                username = _state.value.username,
                password = _state.value.password,
                firstName = _state.value.firstName,
                lastName = _state.value.lastName,
                phone = currentCountryPhone!!.code + _state.value.phone,
                email = _state.value.email,
                gender = _state.value.gender,
                dob = _state.value.dob,
                location = _state.value.location,
                isVendor = _state.value.isVendor,
                busName = _state.value.busName.ifEmpty { null },
                busCategory = _state.value.busCategory.ifEmpty { null },
            ).collectLatest { result ->
                when(result) {
                    is Response.Loading -> {
                        _state.value = RegisterState(isSubmitting = true)
                    }

                    is Response.Success -> {
                        _state.value = RegisterState(
                            data = result.data,
                            submissionSuccess = true,
                            currentDisplay = 5
                        )
                    }

                    is Response.Error -> {
                        _state.value = RegisterState(
                            submissionError =  result.message ?: R.string.unexpected_error,
                            currentDisplay = 5
                        )
                    }
                }
            }
        }
    }

    private fun next(currentFieldSet: Int) {

        when(currentFieldSet) {
            1 -> {
                val firstNameResult = validators.firstName(_state.value.firstName)
                val lastNameResult = validators.lastName(_state.value.lastName)
                val usernameResult = validators.username(_state.value.username, type = Constants.REGISTER_VALIDATE)
                val emailResult = validators.email(_state.value.email)

                val hasError = listOf(
                    usernameResult,
                    firstNameResult,
                    lastNameResult,
                    emailResult
                ).any { !it.successful }

                if(hasError) {
                    _state.value = _state.value.copy(
                        usernameError = usernameResult.errorMessage,
                        firstNameError = firstNameResult.errorMessage,
                        lastNameError = lastNameResult.errorMessage,
                        emailError = emailResult.errorMessage
                    )
                    return
                }

                // needed to check if username is available error
                if(_state.value.usernameError !== null) {
                    return
                }

                _state.value = _state.value.copy(
                    usernameError = null,
                    firstNameError = null,
                    lastNameError = null,
                    emailError = null
                )
            }

            2 -> {
                val locationResult = validators.location(_state.value.location)
                val phoneResult = validators.phone(_state.value.phone)
                val passwordResult = validators.password(_state.value.password, type = Constants.REGISTER_VALIDATE)
                val confirmPasswordResult = validators.confirmPassword(_state.value.password, _state.value.confirmPassword)


                val hasError = listOf(
                    locationResult,
                    phoneResult,
                    passwordResult,
                    confirmPasswordResult,
                ).any { !it.successful }

                if(hasError) {
                    _state.value = _state.value.copy(
                        locationError = locationResult.errorMessage,
                        phoneError = phoneResult.errorMessage,
                        passwordError = passwordResult.errorMessage,
                        confirmPasswordError = confirmPasswordResult.errorMessage
                    )
                    return
                }

                _state.value = _state.value.copy(
                    locationError = null,
                    phoneError = null,
                    passwordError = null,
                    confirmPasswordError = null
                )
            }

            3 -> {
                val dobResult = validators.dob(_state.value.dob)
                val genderResult = validators.gender(_state.value.gender)

                val hasError = listOf(
                    dobResult,
                    genderResult
                ).any { !it.successful }

                if(hasError) {
                    _state.value = _state.value.copy(
                        dobError = dobResult.errorMessage,
                        genderError = genderResult.errorMessage
                    )
                    return
                }
                _state.value = _state.value.copy(
                    dobError = null,
                    genderError = null
                )
            }
        }

        _state.value = _state.value.copy(currentDisplay = _state.value.currentDisplay + 1)

    }
}