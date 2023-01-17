package org.includejoe.markety.base.data.remote

import org.includejoe.markety.feature_authentication.data.remote.dto.LoginDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.RegisterDTO
import org.includejoe.markety.feature_authentication.domain.model.Login
import org.includejoe.markety.feature_authentication.domain.model.Register
import retrofit2.http.Body
import retrofit2.http.POST

interface MarketyAPI {
    @POST("auth/login/")
    suspend fun login(@Body body: Login): LoginDTO

    @POST("auth/register/")
    suspend fun register(@Body body: Register): RegisterDTO
}