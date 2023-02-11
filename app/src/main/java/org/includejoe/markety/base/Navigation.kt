package org.includejoe.markety.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.feature_authentication.presentation.LoginScreen
import org.includejoe.markety.feature_authentication.presentation.RegisterScreen
import org.includejoe.markety.feature_messaging.presentation.MessagesScreen
import org.includejoe.markety.feature_notification.presentation.NotificationsScreen
import org.includejoe.markety.feature_post.presentation.CreatePostScreen
import org.includejoe.markety.feature_post.presentation.HomeScreen
import org.includejoe.markety.feature_post.presentation.PostDetailScreen
import org.includejoe.markety.feature_search.presentation.SearchScreen
import org.includejoe.markety.feature_settings.presentation.SettingsScreen
import org.includejoe.markety.feature_user.presentation.LoggedInUserProfileScreen
import org.includejoe.markety.feature_user.presentation.UserProfileScreen

@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screens.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }

        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screens.PostDetailScreen.route + "/{postId}") {
            PostDetailScreen(navController = navController)
        }

        composable(route = Screens.SearchScreen.route) {
            SearchScreen(navController = navController)
        }

        composable(route = Screens.CreatePostScreen.route) {
            CreatePostScreen(navController = navController)
        }

        composable(route = Screens.NotificationsScreen.route) {
            NotificationsScreen(navController = navController)
        }

        composable(route = Screens.ProfileScreen.route) {
            LoggedInUserProfileScreen(navController = navController)
        }

        composable(route = Screens.ProfileScreen.route + "/{username}") {
            UserProfileScreen(navController = navController)
        }

        composable(route = Screens.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }

        composable(route = Screens.MessagesScreen.route) {
            MessagesScreen(navController = navController)
        }
    }
}