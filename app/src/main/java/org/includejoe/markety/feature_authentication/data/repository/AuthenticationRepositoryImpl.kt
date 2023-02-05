package org.includejoe.markety.feature_authentication.data.repository

import org.includejoe.markety.base.data.remote.MarketyAPI
import org.includejoe.markety.feature_authentication.data.remote.dto.CheckUsernameDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.LoginDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.RegisterDTO
import org.includejoe.markety.feature_authentication.domain.model.Login
import org.includejoe.markety.feature_authentication.domain.model.Register
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val api: MarketyAPI
): AuthenticationRepository {

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
        return api.register(
            Register(
                email = email,
                password = password,
                username = username,
                firstName = firstName,
                lastName = lastName,
                phone = phone,
                gender = gender,
                dob = dob,
                location = location,
                isVendor = isVendor,
                busName = busName,
                busCategory = busCategory
            )
        )
    }

    override suspend fun checkUsername(username: String): CheckUsernameDTO {
        return api.checkUsername(username = username)
    }

    override suspend fun login(username: String, password: String): LoginDTO {
        return api.login(Login(username = username, password = password))
    }
}