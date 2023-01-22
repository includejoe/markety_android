package org.includejoe.markety.base.presentation.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Screens

enum class BottomNavigationItem (
    @DrawableRes val iconId: Int,
    @DrawableRes val isSelectedIconId: Int,
    val route: String,
    val title: Int,
) {
    HOME(
        iconId = R.drawable.ic_home,
        isSelectedIconId = R.drawable.ic_home_fill,
        route = Screens.HomeScreen.route,
        title = R.string.home
    ),
    SEARCH(
        iconId = R.drawable.ic_search,
        isSelectedIconId = R.drawable.ic_search_fill,
        route = Screens.SearchScreen.route,
        title = R.string.search
    ),
    CREATEPOST(
        iconId = R.drawable.ic_add,
        isSelectedIconId = R.drawable.ic_add_fill,
        route = Screens.CreatePostScreen.route,
        title = R.string.post
    ),
    NOTIFICATIONS(
        iconId = R.drawable.ic_bell,
        isSelectedIconId = R.drawable.ic_bell_fill,
        route = Screens.NotificationsScreen.route,
        title = R.string.notifications
    ),
    PROFILE(
        iconId = R.drawable.ic_user,
        isSelectedIconId = R.drawable.ic_user_fill,
        route = Screens.ProfileScreen.route,
        title = R.string.profile
    )
}

@Composable
fun BottomNavigation(
    selectedItem: BottomNavigationItem,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(20.dp)
            .height(50.dp)
            .background(MaterialTheme.colors.background),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (item in BottomNavigationItem.values()) {
            Icon(
                painter = if(item == selectedItem) painterResource(id = item.isSelectedIconId)
                else painterResource(id = item.iconId),
                contentDescription = stringResource(id = item.title),
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .size(if(item == BottomNavigationItem.CREATEPOST) 30.dp else 25.dp)
                    .clickable {
                    navController.navigate(item.route)
                },
                tint = if(item == selectedItem) MaterialTheme.colors.primary
                else MaterialTheme.colors.onBackground
            )
        }
    }
}