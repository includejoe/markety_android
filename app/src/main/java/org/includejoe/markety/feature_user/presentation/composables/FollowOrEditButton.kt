package org.includejoe.markety.feature_user.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.includejoe.markety.base.presentation.theme.ui.spacing

@Composable
fun FollowOrEditButton(
    modifier: Modifier = Modifier,
    text: Int,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(end = MaterialTheme.spacing.sm, top = MaterialTheme.spacing.sm)
            .wrapContentSize()
            .background(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.primary
            )
            .padding(horizontal = 5.dp, vertical = 2.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
            }
            .layoutId("followOrEditBtn"),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = text),
            fontSize = 12.sp,
            color = MaterialTheme.colors.onPrimary,
        )
    }
}