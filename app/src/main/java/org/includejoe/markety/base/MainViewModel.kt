package org.includejoe.markety.base

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _isDarkThemeState = mutableStateOf(true)
    val isDarkThemeState = _isDarkThemeState

    fun toggleTheme() {
        _isDarkThemeState.value = !isDarkThemeState.value
    }
}