package org.includejoe.markety.base.presentation.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Screens

enum class BottomNavigationItem (
    @DrawableRes val iconId: Int,
    val route: String,
) {
    HOME(R.drawable.ic_home, Screens.HomeScreen.route),
    SEARCH(R.drawable.ic_search, Screens.SearchScreen.route),
    CREATEPOST(R.drawable.ic_add, Screens.CreatePostScreen.route),
    NOTIFICATIONS(R.drawable.ic_notification, Screens.NotificationsScreen.route),
    PROFILE(R.drawable.ic_user, Screens.ProfileScreen.route)
}

@Composable
fun BottomNavigation(
    selectedItem: BottomNavigationItem,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colors.background),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (item in BottomNavigationItem.values()) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.route,
                modifier = Modifier
                    .size(45.dp)
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .clickable {
                        navController.navigate(item.route)
                    },
                tint = if(item == selectedItem) MaterialTheme.colors.primary
                else MaterialTheme.colors.onBackground
            )
        }
    }
}