package org.includejoe.markety.base.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: Int,
    textColor: Color = MaterialTheme.colors.onPrimary,
    bgColor: Color = MaterialTheme.colors.primary
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(bgColor)
    ) {
        Text(
            text = stringResource(text),
            modifier = Modifier.padding(vertical = 8.dp),
            color = textColor,
            fontSize = 16.sp
        )
    }
}