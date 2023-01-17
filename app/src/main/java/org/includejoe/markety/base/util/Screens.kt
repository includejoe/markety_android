package org.includejoe.markety.base.util

sealed class Screens(val route: String) {
    object LoginScreen: Screens("login_screen")
    object RegisterScreen: Screens("register_screen")
    object FeedsScreen: Screens("feeds_screen")
    object ProfileScreen: Screens("profile_screen")
}
