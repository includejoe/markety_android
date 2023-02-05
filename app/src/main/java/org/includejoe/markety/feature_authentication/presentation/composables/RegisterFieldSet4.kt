package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
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
    val state = viewModel.state

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextInput(
            value = state.value.busName,
            error = state.value.busNameError,
            onValueChange = {viewModel.onEvent(FormEvent.BusNameChanged(it))},
            inputType = InputType.BusName,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        if(state.value.busNameError != null) {
            ErrorText(text = state.value.busNameError!!)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        BusCategoryInput(
            value = state.value.busCategory,
            error = state.value.busCategoryError,
            onValueChange = {viewModel.onEvent(FormEvent.BusCategoryChanged(it))},
            inputType = InputType.BusCategory,
        )

        if(state.value.busCategoryError != null) {
            ErrorText(text = state.value.busCategoryError!!)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        if(state.value.isSubmitting) {
            CircularProgressIndicator(
                modifier = Modifier.size(25.dp),
                color = MaterialTheme.colors.primary
            )
        } else {
            MButton(
                onClick = {
                    viewModel.onEvent(FormEvent.Register)
                },
                text = stringResource(id = R.string.complete_btn)
            )
        }
    }
}