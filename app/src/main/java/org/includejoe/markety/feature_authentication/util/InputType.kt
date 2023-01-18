package org.includejoe.markety.feature_authentication.util

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.includejoe.markety.R


sealed class InputType(
    val label: Int,
    val icon: ImageVector,
    val keyboardOptions: KeyboardOptions,
    val visualTransformation: VisualTransformation
){
    object Username: InputType(
        label = R.string.username_label,
        icon = Icons.Default.Person,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        visualTransformation = VisualTransformation.None
    )

    object Password: InputType(
        label = R.string.password_label,
        icon = Icons.Default.Lock,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        visualTransformation = PasswordVisualTransformation()
    )
}
