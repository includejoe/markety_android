package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ErrorText(
    text: Int,
) {
    Text(
        text = stringResource(text),
        color = MaterialTheme.colors.error,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.body1,
        fontSize = 12.sp,
        textAlign = TextAlign.End
    )
}