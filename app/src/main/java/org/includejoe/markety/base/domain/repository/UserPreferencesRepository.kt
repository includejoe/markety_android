package org.includejoe.markety.base.domain.repository

import androidx.compose.runtime.State
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import org.includejoe.markety.base.domain.AppState
import java.io.IOException
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val userPreferences: DataStore<Preferences>,
) {
    suspend fun setIsDarkTheme(isDark: Boolean) {
        userPreferences.edit { preferences ->
            preferences[IS_DARK_THEME] = isDark
        }
    }

    suspend fun setLoggedInUser(username: String) {
        userPreferences.edit { preferences ->
            preferences[LOGGED_IN_USER] = username
        }
    }

    suspend fun getIsDarkTheme(): Boolean? {
        val preferences = userPreferences.data.first()
        return preferences[IS_DARK_THEME]
    }

    suspend fun getLoggedInUser(): String? {
        val preferences = userPreferences.data.first()
        return preferences[LOGGED_IN_USER]
    }

    suspend fun clearPreferences() {
        userPreferences.edit { preferences ->
            preferences.clear()
        }
    }

    private companion object {
        val IS_DARK_THEME = booleanPreferencesKey(
            name = "isDarkTheme"
        )

        val LOGGED_IN_USER = stringPreferencesKey(
            name = "loggedInUser"
        )
    }
}