package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.MButton
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_authentication.presentation.RegisterViewModel
import org.includejoe.markety.feature_authentication.util.FormEvent
import org.includejoe.markety.feature_authentication.util.InputType

@Composable
fun RegisterFieldSet4(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
) {
    val busCategoryFR = FocusRequester()
    val focusManager = LocalFocusManager.current
    val state = viewModel.state

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        TextInput(
            value = state.value.busName,
            error = state.value.busNameError,
            onValueChange = {viewModel.onEvent(FormEvent.BusNameChanged(it))},
            inputType = InputType.BusName,
            keyboardActions = KeyboardActions(onNext = {
                busCategoryFR.requestFocus()
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        if(state.value.busNameError != null) {
            Text(
                text = stringResource(state.value.busNameError!!),
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        BusCategoryInput(
            value = state.value.busCategory,
            error = state.value.busCategoryError,
            onValueChange = {viewModel.onEvent(FormEvent.BusCategoryChanged(it))},
            inputType = InputType.BusCategory,
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            })
        )

        if(state.value.busCategoryError != null) {
            Text(
                text = stringResource(state.value.busCategoryError!!),
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        MButton(
            onClick = {
                viewModel.onEvent(FormEvent.Submit)
            },
            text = stringResource(id = R.string.complete_btn)
        )
    }
}