package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.includejoe.markety.base.presentation.theme.ui.DarkGray
import org.includejoe.markety.base.presentation.theme.ui.LightGray
import org.includejoe.markety.feature_authentication.presentation.LoginViewModel
import org.includejoe.markety.feature_authentication.presentation.RegisterViewModel
import org.includejoe.markety.feature_authentication.util.InputType
import org.includejoe.markety.feature_authentication.util.LoginState

@Composable
fun LocationInput(
    value: String,
    error: Any?,
    onValueChange: (String) -> Unit,
    inputType: InputType,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    viewModel: RegisterViewModel
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

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
            .focusOrder(focusRequester ?: FocusRequester()),
        leadingIcon = { Icon(
            imageVector = inputType.icon,
            contentDescription = null,
            tint = MaterialTheme.colors.primary
        ) },
        trailingIcon = {
           IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = "expand",
                    tint = MaterialTheme.colors.onSurface
                )
           }
        },
        placeholder = {
            Text(
                text = stringResource(inputType.label),
                color = if (isSystemInDarkTheme()) DarkGray else LightGray,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions
    )
}