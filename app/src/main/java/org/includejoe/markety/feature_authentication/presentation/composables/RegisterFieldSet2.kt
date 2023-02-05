package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.MButton
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_authentication.presentation.RegisterViewModel
import org.includejoe.markety.feature_authentication.util.FormEvent
import org.includejoe.markety.feature_authentication.util.InputType

@Composable
fun RegisterFieldSet2(
    viewModel: RegisterViewModel,
    modifier: Modifier = Modifier
){
    val locationFR = FocusRequester()
    val passwordFR = FocusRequester()
    val confirmPasswordFR = FocusRequester()
    val focusManager = LocalFocusManager.current
    val state = viewModel.state

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        PhoneInput(
            value = state.value.phone,
            error = state.value.phoneError,
            onValueChange = {viewModel.onEvent(FormEvent.PhoneChanged(it))},
            inputType = InputType.Phone,
            keyboardActions = KeyboardActions(onNext = {
                locationFR.requestFocus()
            }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone
            ),
            viewModel = viewModel,
        )

        if(state.value.phoneError != null) {
            ErrorText(text = state.value.phoneError!!)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        AutoCompleteLocationInput(
            value = state.value.location,
            error = state.value.locationError,
            onValueChange = {viewModel.onEvent(FormEvent.LocationChanged(it))},
            inputType = InputType.Location,
            keyboardActions = KeyboardActions(onNext = {
                passwordFR.requestFocus()
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            focusRequester = locationFR,
            viewModel = viewModel
        )

        if(state.value.locationError != null) {
            ErrorText(text = state.value.locationError!!)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        PasswordInput(
            value = state.value.password,
            error = state.value.passwordError,
            onValueChange = {viewModel.onEvent(FormEvent.PasswordChanged(it))},
            inputType = InputType.Password,
            keyboardActions = KeyboardActions(onNext = {
                confirmPasswordFR.requestFocus()
            }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            focusRequester = passwordFR
        )

        if(state.value.passwordError != null) {
            ErrorText(text = state.value.passwordError!!)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        PasswordInput(
            value = state.value.confirmPassword,
            error = state.value.confirmPasswordError,
            onValueChange = {viewModel.onEvent(FormEvent.ConfirmPasswordChanged(it))},
            inputType = InputType.ConfirmPassword,
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            focusRequester = confirmPasswordFR
        )

        if(state.value.confirmPasswordError != null) {
            ErrorText(text = state.value.confirmPasswordError!!)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        MButton(
            onClick = {
                viewModel.onEvent(FormEvent.Next)
            },
            text = stringResource(id = R.string.next_btn)
        )
    }
}