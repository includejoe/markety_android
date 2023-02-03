package org.includejoe.markety.base

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    val isDarkTheme = mutableStateOf(false)
    val loggedInUser = mutableStateOf<String?>("")
    val isAuthenticated = mutableStateOf(false)
}