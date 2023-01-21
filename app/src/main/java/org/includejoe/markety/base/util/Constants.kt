package org.includejoe.markety.base.util

object Constants {
    const val BASE_URL = "https://marketyapi.up.railway.app/api/v1/"

    const val LOGIN_VALIDATE = "login"
    const val REGISTER_VALIDATE = "register"
    const val USERNAME_REGEX = """^[\w](?!.*?\.{2})[\w.]{1,28}[\w]${'$'}"""

    const val ACCESS_TOKEN_KEY = "access_token"
    const val REFRESH_TOKEN_KEY = "refresh_token"
    const val APP_SHARED_PREFERENCES = "app_shared_preferences"
}