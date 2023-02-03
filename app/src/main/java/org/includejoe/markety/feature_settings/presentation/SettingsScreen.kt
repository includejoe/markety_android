package org.includejoe.markety.feature_settings.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_settings.presentation.composables.SettingsTopBar
import org.includejoe.markety.feature_settings.util.SettingsItem

@Composable
fun SettingsScreen(
    navController: NavHostController,
    viewModel: SettingsViewModel = hiltViewModel(),
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SettingsTopBar(navController = navController)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(MaterialTheme.spacing.sm)
                .verticalScroll(rememberScrollState())
        ) {
            ThemeItem(
                checkedValue = viewModel.baseApp.isDarkTheme.value,
                onCheckedChange = { viewModel.toggleTheme() }
            )
            ItemDisplay(item = SettingsItem.LOGOUT)
        }
    }
}

@Composable
private fun ThemeItem(
    checkedValue: Boolean,
    onCheckedChange: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                imageVector = SettingsItem.DARK_THEME.icon,
                contentDescription = stringResource(id = SettingsItem.DARK_THEME.label),
                tint = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))
            Text(
                text = stringResource(id = SettingsItem.DARK_THEME.label),
                color = MaterialTheme.colors.onBackground,
                fontSize = MaterialTheme.typography.body1.fontSize
            )
        }

        Switch(
            checked = checkedValue,
            onCheckedChange = {
                onCheckedChange()
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.secondary
            )
        )
    }
}

@Composable
private fun ItemDisplay(
    item: SettingsItem,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .clickable {},
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                imageVector = item.icon,
                contentDescription = stringResource(id = item.label),
                tint = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))
            Text(
                text = stringResource(id = item.label),
                color = MaterialTheme.colors.onBackground,
                fontSize = MaterialTheme.typography.body1.fontSize
            )
        }
    }
}