package org.includejoe.markety.feature_authentication.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.feature_authentication.data.remote.dto.LoginDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.toLogin
import org.includejoe.markety.feature_authentication.domain.model.Login
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(
        username: String,
        password: String
    ): Flow<Response<Login>> = flow {
        try {
            emit(Response.Loading<Login>())
            val data = repository.login(username, password).toLogin()
            emit(Response.Success<Login>(data))
        } catch (e: HttpException) {
            emit(Response.Error<Login>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Response.Error<Login>("Couldn't reach server, check your internet connection"))
        }
    }
}