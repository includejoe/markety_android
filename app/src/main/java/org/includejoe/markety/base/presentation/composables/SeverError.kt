package org.includejoe.markety.base.presentation.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ServerError(error: Any, toast: Boolean) {
    when(error) {
        is Int -> {
            if(toast) {
                Toast(message = stringResource(error))
            } else {
                Text(
                    text = stringResource(id = error),
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1
                )
            }
        }

        is String -> {
            if(toast) {
                Toast(message = error)
            } else {
                Text(
                    text = error,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}