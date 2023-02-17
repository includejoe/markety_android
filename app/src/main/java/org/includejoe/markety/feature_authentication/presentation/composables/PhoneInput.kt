package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.includejoe.markety.base.presentation.composables.CountryPickerView
import org.includejoe.markety.base.presentation.theme.ui.LightGray
import org.includejoe.markety.feature_authentication.presentation.RegisterViewModel
import org.includejoe.markety.feature_authentication.util.InputType

@Composable
fun PhoneInput(
    value: String,
    error: Any?,
    onValueChange: (String) -> Unit,
    inputType: InputType,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    viewModel: RegisterViewModel,
) {

    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        isError = error != null,
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .height(50.dp)
            .focusRequester(focusRequester ?: FocusRequester()),
        leadingIcon = {
            viewModel.currentCountryPhone?.let {
                CountryPickerView(
                    selectedCountry = it,
                    onSelection = { selectedCountry ->
                        viewModel.currentCountryPhone = selectedCountry
                    },
                    countries = viewModel.countriesList
                )
            }
        },
        placeholder = {
            Text(
                text = stringResource(inputType.label),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body1
            )
      },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.body1,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
    )

}