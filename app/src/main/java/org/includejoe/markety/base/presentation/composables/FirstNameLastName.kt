package org.includejoe.markety.base.presentation.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun FirstNameLastName(
    firstName: String,
    lastName: String
) {
    Text(
        text = "$firstName $lastName",
        color = MaterialTheme.colors.onBackground,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.body1
    )
}