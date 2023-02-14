package org.includejoe.markety.base.domain.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.includejoe.markety.feature_user.domain.model.User
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val userPreferences: DataStore<Preferences>,
) {
    suspend fun setIsDarkTheme(isDark: Boolean) {
        userPreferences.edit { preferences ->
            preferences[IS_DARK_THEME] = isDark
        }
    }

    suspend fun getIsDarkTheme(): Boolean? {
        val preferences = userPreferences.data.first()
        return preferences[IS_DARK_THEME]
    }


    suspend fun setUserDetails(userDetails: User) {
        userPreferences.edit { preferences ->
            preferences[USER_DETAILS] = Json.encodeToString(userDetails)
        }
    }

    val userDetailsFlow: Flow<User?> = userPreferences.data.map { preferences ->
        val serializedData = preferences[USER_DETAILS] ?: return@map null
        Json.decodeFromString<User>(serializedData)
    }

    suspend fun clearPreferences() {
        userPreferences.edit { preferences ->
            preferences.remove(USER_DETAILS)
        }
    }

    private companion object {
        val IS_DARK_THEME = booleanPreferencesKey(
            name = "isDarkTheme"
        )

        val USER_DETAILS = stringPreferencesKey(
            name = "userDetails"
        )
    }
}