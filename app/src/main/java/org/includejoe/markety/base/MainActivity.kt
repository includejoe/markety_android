package org.includejoe.markety.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.includejoe.markety.base.presentation.theme.ui.MarketyTheme
import org.includejoe.markety.base.domain.repository.UserPreferencesRepository
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.base.util.TokenManager
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
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var tokenManager: TokenManager
    @Inject lateinit var baseApp: BaseApplication
    @Inject lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        lifecycleScope.launch {
            checkTheme()
            setUserDetails()
        }
        setContent {
            MarketyTheme(darkTheme = baseApp.isDarkTheme.value) {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    val startDestination = if (baseApp.isAuthenticated.value) {
                        Screens.HomeScreen.route
                    } else {
                        Screens.LoginScreen.route
                    }

                    Navigation(
                        navController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }

    private suspend fun setUserDetails() {
        userPreferencesRepository.userDetailsFlow.collect {
            baseApp.userDetails.value = it
        }
    }

    private suspend fun checkTheme() {
        if(userPreferencesRepository.getIsDarkTheme() !== null) {
            baseApp.isDarkTheme.value = if(userPreferencesRepository.getIsDarkTheme()!!)
                userPreferencesRepository.getIsDarkTheme()!!
            else false
        }
    }
}