package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.includejoe.markety.feature_authentication.util.InputType

@Composable
fun PasswordInput(
    value: String,
    error: Any?,
    onValueChange: (String) -> Unit,
    inputType: InputType,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions
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
            .focusOrder(focusRequester ?: FocusRequester()),
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
                    tint = MaterialTheme.colors.onSurface
                )
            }
        },
        label = { Text(text = stringResource(inputType.label), color = MaterialTheme.colors.onSurface) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
        singleLine = true,
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = if(isVisible) VisualTransformation.None
                               else PasswordVisualTransformation(),
        keyboardActions = keyboardActions
    )
}