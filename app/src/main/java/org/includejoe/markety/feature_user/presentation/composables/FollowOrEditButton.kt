package org.includejoe.markety.feature_user.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.includejoe.markety.base.presentation.theme.ui.spacing

@Composable
fun FollowOrEditButton(
    modifier: Modifier = Modifier,
    text: Int,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .padding(end = MaterialTheme.spacing.sm, top = MaterialTheme.spacing.sm)
            .clip(MaterialTheme.shapes.small)
            .height(25.dp)
            .layoutId("followOrEditBtn"),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        contentPadding = PaddingValues(
            vertical = 0.dp,
            horizontal = 5.dp
        ),
    ) {
        Text(
            text = stringResource(id = text),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.body1
        )
    }
}