package org.includejoe.markety.base.domain

import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val preferences: AppSharedPreferences,
//    private val authRepository: AuthenticationRepository
) {

    fun getAccessToken(): String? = preferences.getAccessToken()

    fun getRefreshToken(): String? = preferences.getRefreshToken()

//    fun refreshToken(token: String): String = authRepository.refreshToken(token)

    fun logout() {
        preferences.setAccessToken("")
    }
}