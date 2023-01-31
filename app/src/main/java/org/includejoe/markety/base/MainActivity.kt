package org.includejoe.markety.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.State
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.includejoe.markety.base.presentation.theme.ui.MarketyTheme
import org.includejoe.markety.base.domain.AppState
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_authentication.presentation.LoginScreen
import org.includejoe.markety.feature_authentication.presentation.RegisterScreen
import org.includejoe.markety.feature_messaging.presentation.MessagesScreen
import org.includejoe.markety.feature_notification.presentation.NotificationsScreen
import org.includejoe.markety.feature_post.presentation.CreatePostScreen
import org.includejoe.markety.feature_post.presentation.HomeScreen
import org.includejoe.markety.feature_search.presentation.SearchScreen
import org.includejoe.markety.feature_user.presentation.ProfileScreen
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var tokenManager: TokenManager
    @Inject lateinit var appState: State<AppState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MarketyTheme(darkTheme = appState.value.isDarkTheme) {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val startDestination = if (appState.value.isAuthenticated) {
                        Screens.HomeScreen.route
                    } else {
                        Screens.LoginScreen.route
                    }

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
                            ProfileScreen(navController = navController /*, mainViewModel=mainViewModel */)
                        }

                        composable(route = Screens.MessagesScreen.route) {
                            MessagesScreen(navController = navController)
                        }
                    }
                }

            }
        }
    }
}