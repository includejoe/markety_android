package org.includejoe.markety.base.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Screens

@Composable
fun BottomNavigation(
    selectedItem: NavigationItem,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(20.dp)
            .height(60.dp)
            .background(MaterialTheme.colors.primaryVariant),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (item in NavigationItem.values()) {
            Icon(
                imageVector = if(item == selectedItem) item.isSelectedIcon
                else item.icon,
                contentDescription = stringResource(id = item.title),
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .size(if (item == NavigationItem.CREATE_POST) 35.dp else 30.dp)
                    .size(30.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        navController.navigate(item.route)
                    },
                tint = if(item == selectedItem) MaterialTheme.colors.primary
                else MaterialTheme.colors.onBackground
            )
        }
    }
}

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
    CREATE_POST(
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
    )
}