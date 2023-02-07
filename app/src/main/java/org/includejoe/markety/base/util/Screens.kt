package org.includejoe.markety.base.util

sealed class Screens(val route: String) {
    object LoginScreen: Screens("login_screen")
    object RegisterScreen: Screens("register_screen")
    object HomeScreen: Screens("home_screen")
    object SearchScreen: Screens("search_screen")
    object CreatePostScreen: Screens("create_post_screen")
    object NotificationsScreen: Screens("notifications_screen")
    object ProfileScreen: Screens("profile_screen")
    object MessagesScreen: Screens("messages_screen")
    object SettingsScreen: Screens("settings_screen")
    object PostDetailScreen: Screens("post_detail_screen")
}
