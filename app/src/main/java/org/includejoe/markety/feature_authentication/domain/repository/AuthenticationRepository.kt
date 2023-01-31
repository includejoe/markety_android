package org.includejoe.markety.feature_authentication.domain.repository

import org.includejoe.markety.feature_authentication.data.remote.dto.CheckUsernameDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.LoginDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.RegisterDTO
import org.includejoe.markety.feature_authentication.domain.model.RefreshTokenResponse

interface AuthenticationRepository {

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

    suspend fun checkUsername(username: String): CheckUsernameDTO

    suspend fun login(username: String, password: String): LoginDTO

    suspend fun getNewAccessToken(refreshToken: String): RefreshTokenResponse
}