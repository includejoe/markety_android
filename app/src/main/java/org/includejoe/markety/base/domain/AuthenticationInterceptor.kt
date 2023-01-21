package org.includejoe.markety.base.domain

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val sessionManager: SessionManager,
//    private val authRepository: AuthenticationRepository
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var accessToken = sessionManager.getAccessToken()

        val response = chain.proceed(newRequestWithAccessToken(accessToken, request))

        if(response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            val newAccessToken = sessionManager.getAccessToken()
            if(newAccessToken != accessToken) {
                return chain.proceed(newRequestWithAccessToken(accessToken, request))
            } else {
//                accessToken = refreshToken()
                if(accessToken.isNullOrBlank()) {
                    sessionManager.logout()
                    return response
                }
                return chain.proceed(newRequestWithAccessToken(accessToken, request))
            }
        }

        return response
    }

    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
        request
            .newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()

//    private fun refreshToken(): String? {
//        synchronized(this) {
//            val token = sessionManager.getRefreshToken()
//            token?.let {
//                return  authRepository.refreshToken(token)
//            } ?: return null
//        }
//    }
}