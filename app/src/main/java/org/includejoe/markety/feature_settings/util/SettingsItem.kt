package org.includejoe.markety.feature_settings.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import org.includejoe.markety.R

enum class SettingsItem (
    val label: Int,
    val icon: ImageVector,
) {

    DARK_THEME(
        label = R.string.dark_theme,
        icon = Icons.Default.DarkMode,
    ),

    LOGOUT(
    label = R.string.logout,
    icon = Icons.Default.Logout,
    )
}