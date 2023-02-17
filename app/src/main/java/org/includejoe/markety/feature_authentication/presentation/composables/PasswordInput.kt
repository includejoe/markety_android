package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.includejoe.markety.base.presentation.theme.ui.DarkGray
import org.includejoe.markety.base.presentation.theme.ui.LightGray
import org.includejoe.markety.feature_authentication.util.InputType

@Composable
fun PasswordInput(
    value: String,
    error: Any?,
    onValueChange: (String) -> Unit,
    inputType: InputType,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    keyboardOptions: KeyboardOptions,
){
    var isVisible by remember { mutableStateOf(false)}

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
        leadingIcon = { Icon(
            imageVector = inputType.icon,
            contentDescription = null,
            tint = MaterialTheme.colors.primary
        ) },
        trailingIcon = {
            val icon = if(isVisible) Icons.Filled.Visibility
                       else Icons.Filled.VisibilityOff
            val desc = if(isVisible) "hide password" else "show password"
            IconButton(onClick = {isVisible = !isVisible}) {
                Icon(
                    imageVector = icon,
                    contentDescription = desc,
                    tint = MaterialTheme.colors.onBackground
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
        visualTransformation = if(isVisible) VisualTransformation.None
                               else PasswordVisualTransformation(),
        keyboardActions = keyboardActions
    )
}