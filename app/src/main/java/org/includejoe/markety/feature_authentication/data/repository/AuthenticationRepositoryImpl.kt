package org.includejoe.markety.feature_authentication.data.repository


import org.includejoe.markety.base.data.remote.MarketyAPI
import org.includejoe.markety.feature_authentication.data.remote.dto.LoginDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.RegisterDTO
import org.includejoe.markety.feature_authentication.domain.model.Login
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val api: MarketyAPI
): AuthenticationRepository {
    override suspend fun isUserAuthenticated(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun register(
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
    ): RegisterDTO {
        TODO("Not yet implemented")
    }

    override suspend fun login(username: String, password: String): LoginDTO {
        return api.login(Login(username = username, password = password))
    }

    override fun refreshToken(refreshToken: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Boolean {
        TODO("Not yet implemented")
    }


}