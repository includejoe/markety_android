package org.includejoe.markety.feature_settings.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_settings.util.SettingsItem
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.ConfirmationDialog

@Composable
fun Logout(
    logoutFunction: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { showDialog = true },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                imageVector = SettingsItem.LOGOUT.icon,
                contentDescription = stringResource(id = SettingsItem.LOGOUT.label),
                tint = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))
            Text(
                text = stringResource(id = SettingsItem.LOGOUT.label),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body1
            )
        }
    }

    if(showDialog) {
        ConfirmationDialog(yes = logoutFunction, text = R.string.confirm_logout) {
            showDialog = false
        }
    }
}