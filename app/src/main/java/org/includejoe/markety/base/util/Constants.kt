package org.includejoe.markety.base.util

import java.text.SimpleDateFormat
import java.util.*

object Constants {
    /* production server */
    const val MARKETY_BASE_URL = "https://marketyapi.up.railway.app/api/v1/"

        /* development server */
//    const val MARKETY_BASE_URL = "http://10.0.2.2:8000/api/v1/"

    const val LOGIN_VALIDATE = "login"
    const val REGISTER_VALIDATE = "register"
    const val USERNAME_REGEX = """^[\w](?!.*?\.{2})[\w.]{1,28}[\w]${'$'}"""

    const val JWT_KEY = "jwt"
    const val GOOGLE_MAPS_API_KEY = "AIzaSyACARrKET7OBzoC6oE4gl_cnH9ocdrQCb8"

    const val DATASTORE_USER_PREFERENCES = "user_preferences"
    const val ENCRYPTED_SHARED_PREFS = "encrypted_shared_prefs"

    val APP_DATE_FORMAT = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
    val API_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.US)
}