package org.includejoe.markety.feature_authentication.domain.repository

import org.includejoe.markety.feature_authentication.data.remote.dto.LoginDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.RegisterDTO

interface AuthenticationRepository {

    suspend fun isUserAuthenticated(): Boolean

    suspend fun register(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String,
        phone: String,
        gender: String,
        dob: String,
        location: String,
        isVendor: Boolean,
        busName: String?,
        busCategory: String?
    ): RegisterDTO

    suspend fun login(username: String, password: String): LoginDTO

    suspend fun logout(): Boolean
}