package org.includejoe.markety.base.domain

import android.content.SharedPreferences
import org.includejoe.markety.base.util.Constants
import javax.inject.Inject

class AppSharedPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun getAccessToken(): String? = sharedPreferences.getString(Constants.ACCESS_TOKEN_KEY, null)

    fun setAccessToken(accessToken: String) {
        sharedPreferences.edit().putString(Constants.ACCESS_TOKEN_KEY, accessToken).apply()
    }

    fun getRefreshToken(): String? = sharedPreferences.getString(Constants.REFRESH_TOKEN_KEY, null)

    fun setRefreshToken(refreshToken: String) {
        sharedPreferences.edit().putString(Constants.REFRESH_TOKEN_KEY, refreshToken).apply()
    }
}