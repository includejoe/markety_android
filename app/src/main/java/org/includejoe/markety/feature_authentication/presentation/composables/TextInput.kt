package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import org.includejoe.markety.feature_authentication.util.InputType

@Composable
fun TextInput(
    inputType: InputType,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions
) {
    var value by remember {
        mutableStateOf("")
    }

    TextField(
        value = value,
        onValueChange = { value = it },
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .focusOrder(focusRequester ?: FocusRequester()),
        leadingIcon = { Icon(
            imageVector = inputType.icon,
            contentDescription = null,
            tint = MaterialTheme.colors.primary
        ) },
        label = { Text(text = stringResource(inputType.label), color = MaterialTheme.colors.onSurface)},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
        singleLine = true,
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = inputType.visualTransformation,
        keyboardActions = keyboardActions
    )

}