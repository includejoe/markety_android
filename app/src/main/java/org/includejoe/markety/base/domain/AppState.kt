package org.includejoe.markety.base.domain

data class AppState(
    var isDarkTheme: Boolean = true,
    var isAuthenticated: Boolean = false,
    var loggedInUser: String? = null,
)
