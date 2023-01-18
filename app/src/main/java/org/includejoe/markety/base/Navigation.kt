package org.includejoe.markety.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.feature_authentication.presentation.LoginScreen


@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
    ) {
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
    }
}