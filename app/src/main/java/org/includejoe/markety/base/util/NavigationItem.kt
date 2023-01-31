package org.includejoe.markety.base.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import org.includejoe.markety.R

enum class NavigationItem (
    val icon: ImageVector,
    val isSelectedIcon: ImageVector,
    val route: String,
    val title: Int,
) {
    HOME(
        icon = Icons.Outlined.Home,
        isSelectedIcon = Icons.Filled.Home,
        route = Screens.HomeScreen.route,
        title = R.string.home
    ),
    SEARCH(
        icon = Icons.Outlined.Search,
        isSelectedIcon = Icons.Filled.Search,
        route = Screens.SearchScreen.route,
        title = R.string.search
    ),
    CREATEPOST(
        icon = Icons.Outlined.AddBox,
        isSelectedIcon = Icons.Filled.AddBox,
        route = Screens.CreatePostScreen.route,
        title = R.string.post
    ),
    NOTIFICATIONS(
        icon = Icons.Outlined.Notifications,
        isSelectedIcon = Icons.Filled.Notifications,
        route = Screens.NotificationsScreen.route,
        title = R.string.notifications
    ),
    PROFILE(
        icon = Icons.Outlined.Person,
        isSelectedIcon = Icons.Filled.Person,
        route = Screens.ProfileScreen.route,
        title = R.string.profile
    ),
    MESSAGES(
        icon = Icons.Outlined.Email,
        isSelectedIcon = Icons.Filled.Email,
        route = Screens.MessagesScreen.route,
        title = R.string.messages
    ),
    SETTINGS(
        icon = Icons.Outlined.Menu,
        isSelectedIcon = Icons.Filled.Menu,
        route = Screens.SettingsScreen.route,
        title = R.string.settings
    )
}