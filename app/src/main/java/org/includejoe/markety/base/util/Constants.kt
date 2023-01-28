package org.includejoe.markety.base.util

object Constants {
    const val MARKETY_BASE_URL = "https://marketyapi.up.railway.app/api/v1/"

    const val LOGIN_VALIDATE = "login"
    const val REGISTER_VALIDATE = "register"
    const val USERNAME_REGEX = """^[\w](?!.*?\.{2})[\w.]{1,28}[\w]${'$'}"""

    const val ACCESS_TOKEN_KEY = "access_token"
    const val REFRESH_TOKEN_KEY = "refresh_token"
    const val GOOGLE_MAPS_API_KEY = "AIzaSyACARrKET7OBzoC6oE4gl_cnH9ocdrQCb8"

    const val USER_AUTHENTICATED = "is_user_authenticated"

    const val ENCRYPTED_SHARED_PREFS = "encrypted_shared_prefs"
    const val USER_SHARED_PREFS = "user_shared_prefs"
}