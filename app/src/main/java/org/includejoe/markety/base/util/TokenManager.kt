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
import java.time.LocalDate
import javax.inject.Inject

// TODO: Include JWT Decoder here
/* TODO: try to get accessToken, verify with jwt decoder and
    if it is expired, make request with refreshToken and
    return new accessToken else just use accessToken and
    improve accessToken lifespan */

@RequiresApi(Build.VERSION_CODES.O)
class TokenManager @Inject constructor(
    context: Context,
    private var encryptedSharedPrefs: SharedPreferences,
    private val appState: State<AppState>
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

        // TODO: Find a better way of extracting expiration date value
        var expiresAt: String

        val jwt = encryptedSharedPrefs.getString(Constants.JWT_KEY, null)

        if(jwt !== null) {
            JWT.decode(jwt).tap {
                // Extract only date string
                expiresAt = it.expiresAt().toString().slice(12..30)
                LocalDate.now()
            }
        }
    }


    fun readToken(): String? {
        return encryptedSharedPrefs.getString(Constants.JWT_KEY, null)
    }


    fun saveToken(jwt: String?) {
        val editor = encryptedSharedPrefs.edit()
        editor.putString(Constants.JWT_KEY, jwt)
        editor.apply()
    }

    fun removeToken() {
        val editor = encryptedSharedPrefs.edit()
        editor.remove(Constants.JWT_KEY)
        editor.apply()
    }
}