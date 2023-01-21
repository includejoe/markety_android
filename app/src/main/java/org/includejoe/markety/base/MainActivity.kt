package org.includejoe.markety.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.includejoe.markety.base.presentation.theme.ui.MarketyTheme
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.feature_authentication.presentation.LoginScreen
import org.includejoe.markety.feature_post.presentation.FeedsScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MarketyTheme(darkTheme = true) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screens.LoginScreen.route
                ) {
                    composable(route = Screens.LoginScreen.route) {
                        LoginScreen(navController = navController)
                    }

                    composable(route = Screens.FeedsScreen.route) {
                        FeedsScreen()
                    }
                }
            }
        }
    }
}
