package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
        horizontalAlignment = Alignment.CenterHorizontally
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
            ErrorText(text = state.value.dobError!!)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        GenderInput(
            value = state.value.gender,
            error = state.value.genderError,
            onValueChange = { viewModel.onEvent(FormEvent.GenderChanged(it)) },
            inputType = InputType.Gender,
        )

        if (state.value.genderError != null) {
            ErrorText(text = state.value.genderError!!)
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
}