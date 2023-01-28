package org.includejoe.markety.base.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject

class TokenManager @Inject constructor(
    context: Context,
    private var encryptedSharedPrefs: SharedPreferences
) {

    init {
        val masterKey = MasterKey
            .Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        encryptedSharedPrefs = EncryptedSharedPreferences.create(
            context,
            Constants.ENCRYPTED_SHARED_PREFS,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveOrRemoveTokens(accessToken: String?, refreshToken: String?) {
        if(refreshToken == null) {
            removeTokens()
        } else {
            saveTokens(accessToken, refreshToken)
        }
    }

    fun saveNewAccessToken(accessToken: String?) {
        val editor = encryptedSharedPrefs.edit()
        editor.putString(Constants.ACCESS_TOKEN_KEY, accessToken)
        editor.apply()
    }

    fun readAccessToken(): String? {
        return encryptedSharedPrefs.getString(Constants.ACCESS_TOKEN_KEY, null)
    }

    fun readRefreshToken(): String? {
        return encryptedSharedPrefs.getString(Constants.REFRESH_TOKEN_KEY, null)
    }

    fun setIsAuthenticated(isAuthenticated: Boolean) {
        val editor = encryptedSharedPrefs.edit()
        editor.putBoolean(Constants.USER_AUTHENTICATED, isAuthenticated)
        editor.apply()
    }

    fun readIsAuthenticated(): Boolean {
        return encryptedSharedPrefs.getBoolean(Constants.USER_AUTHENTICATED, false)
    }

    private fun saveTokens(accessToken: String?, refreshToken: String?) {
        val editor = encryptedSharedPrefs.edit()
        editor.putString(Constants.ACCESS_TOKEN_KEY, accessToken)
        editor.putString(Constants.REFRESH_TOKEN_KEY, refreshToken)
        editor.apply()
    }

    private fun removeTokens() {
        val editor = encryptedSharedPrefs.edit()
        editor.remove(Constants.ACCESS_TOKEN_KEY)
        editor.remove(Constants.REFRESH_TOKEN_KEY)
        editor.apply()
    }
}