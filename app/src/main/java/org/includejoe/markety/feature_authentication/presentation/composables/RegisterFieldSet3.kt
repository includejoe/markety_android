package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.MButton
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.base.util.DatePicker
import org.includejoe.markety.feature_authentication.presentation.RegisterViewModel
import org.includejoe.markety.feature_authentication.util.FormEvent
import org.includejoe.markety.feature_authentication.util.InputType

@Composable
fun RegisterFieldSet3(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel
) {
    val state = viewModel.state
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        TextInput(
            modifier = Modifier.clickable {
                DatePicker(
                    context = context,
                    date = viewModel.state.value.dob,
                    setDate = { viewModel.onEvent(FormEvent.DobChanged(it)) }
                ).showDatePickerDialog()
            },
            value = state.value.dob,
            error = state.value.dobError,
            onValueChange = { viewModel.onEvent(FormEvent.DobChanged(it)) },
            inputType = InputType.Dob,
            isEnabled = false,
        )

        if (state.value.dobError != null) {
            Text(
                text = stringResource(state.value.dobError!!),
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        GenderInput(
            value = state.value.gender,
            error = state.value.genderError,
            onValueChange = { viewModel.onEvent(FormEvent.GenderChanged(it)) },
            inputType = InputType.Gender,
        )

        if (state.value.genderError != null) {
            Text(
                text = stringResource(state.value.genderError!!),
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))
        
        IsVendorCheckBox(viewModel = viewModel)

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        if(viewModel.state.value.isVendor) {
            MButton(
                onClick = {
                    viewModel.onEvent(FormEvent.Next)
                },
                text = stringResource(id = R.string.next_btn)
            )
        } else {
            MButton(
                onClick = {
                    viewModel.onEvent(FormEvent.Submit)
                },
                text = stringResource(id = R.string.complete_btn)
            )
        }


    }
}