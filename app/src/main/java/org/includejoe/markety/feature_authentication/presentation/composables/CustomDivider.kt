package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomDivider(
    modifier: Modifier = Modifier
) {
    Divider(
        color = MaterialTheme.colors.surface.copy(alpha = 0.7f),
        thickness = 1.dp,
        modifier = modifier.padding(top = 38.dp)
    )
}