package org.includejoe.markety.base.util

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import io.github.nefilim.kjwt.JWT
import org.includejoe.markety.base.domain.AppState
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class TokenManager @Inject constructor(
    context: Context,
    private var encryptedSharedPrefs: SharedPreferences,
    private val appState: State<AppState>
) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.UK)
    private val currentDate = Date()
    private lateinit var expiresAtStr: String
    private lateinit var expiresAtDate: Date

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

        val jwt = encryptedSharedPrefs.getString(Constants.JWT_KEY, null)

        if(jwt !== null) {
            JWT.decode(jwt).tap {
                // TODO: Find a better way of extracting expiration date value
                expiresAtStr = it.expiresAt().toString().slice(12..30)
                expiresAtDate = dateFormat.parse(expiresAtStr)!!
            }

            // Check if JWT hasn't expired
            if(expiresAtDate.before(currentDate)) {
                removeToken()
                appState.value.isAuthenticated = false
            } else {
                appState.value.isAuthenticated = true
            }
        }
    }



    fun login(jwt: String?) {
        saveToken(jwt)
        appState.value.isAuthenticated = true
    }

    fun readToken(): String? {
        return encryptedSharedPrefs.getString(Constants.JWT_KEY, null)
    }

    fun logOut() {
        removeToken()
        appState.value.isAuthenticated = false
    }


    private fun saveToken(jwt: String?) {
        val editor = encryptedSharedPrefs.edit()
        editor.putString(Constants.JWT_KEY, jwt)
        editor.apply()
    }

    private fun removeToken() {
        val editor = encryptedSharedPrefs.edit()
        editor.remove(Constants.JWT_KEY)
        editor.apply()
    }
}