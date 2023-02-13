package org.includejoe.markety.base.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.base.util.MenuItem

@Composable
fun BottomSheetMenu(
    modifier: Modifier = Modifier,
    menuItems: List<MenuItem>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(
                horizontal = MaterialTheme.spacing.md,
                vertical = MaterialTheme.spacing.sm
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(
            thickness = 3.dp,
            modifier = Modifier
                .width(30.dp)
                .background(
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(2.dp)
                ),
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))
        for(item in menuItems) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.md)
                    .clickable {
                        item.onClick()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.label,
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.sm))
                Text(
                    text = item.label,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}