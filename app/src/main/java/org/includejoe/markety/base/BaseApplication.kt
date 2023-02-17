package org.includejoe.markety.base

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp
import org.includejoe.markety.feature_user.domain.model.User

@HiltAndroidApp
class BaseApplication : Application() {
    val isDarkTheme = mutableStateOf(false)
    val isAuthenticated = mutableStateOf(false)
    var userDetails = mutableStateOf<User?>(User())
}