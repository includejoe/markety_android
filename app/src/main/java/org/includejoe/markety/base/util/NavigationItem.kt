package org.includejoe.markety.base.util

import androidx.annotation.DrawableRes
import org.includejoe.markety.R

enum class NavigationItem (
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
    ),
    MESSAGES(
        iconId = R.drawable.ic_message,
        isSelectedIconId = R.drawable.ic_message_fill,
        route = Screens.MessagesScreen.route,
        title = R.string.profile
    )
}