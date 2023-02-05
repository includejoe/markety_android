package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.layout.*
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
fun RegisterFieldSet1(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
) {
    val lastNameFR = FocusRequester()
    val usernameFR = FocusRequester()
    val emailFR = FocusRequester()
    val focusManager = LocalFocusManager.current
    val state = viewModel.state

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        TextInput(
            value = state.value.firstName,
            error = state.value.firstNameError,
            onValueChange = {viewModel.onEvent(FormEvent.FirstNameChanged(it))},
            inputType = InputType.FirstName,
            keyboardActions = KeyboardActions(onNext = {
                lastNameFR.requestFocus()
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        if(state.value.firstNameError != null) {
            ErrorText(text = state.value.firstNameError!!)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        TextInput(
            value = state.value.lastName,
            error = state.value.lastNameError,
            onValueChange = {viewModel.onEvent(FormEvent.LastNameChanged(it))},
            inputType = InputType.LastName,
            keyboardActions = KeyboardActions(onNext = {
                usernameFR.requestFocus()
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            focusRequester = lastNameFR
        )

        if(state.value.lastNameError != null) {
            ErrorText(text = state.value.lastNameError!!)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        UsernameInput(
            value = state.value.username,
            error = state.value.usernameError,
            onValueChange = {viewModel.onEvent(FormEvent.UsernameChanged(it))},
            inputType = InputType.Username,
            keyboardActions = KeyboardActions(onNext = {
                emailFR.requestFocus()
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            focusRequester = usernameFR,
            viewModel = viewModel
        )

        if(state.value.usernameError != null) {
            ErrorText(text = state.value.usernameError!!)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        TextInput(
            value = state.value.email,
            error = state.value.emailError,
            onValueChange = {viewModel.onEvent(FormEvent.EmailChanged(it))},
            inputType = InputType.Email,
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Email
            ),
            focusRequester = emailFR
        )

        if(state.value.emailError != null) {
            ErrorText(text = state.value.emailError!!)
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