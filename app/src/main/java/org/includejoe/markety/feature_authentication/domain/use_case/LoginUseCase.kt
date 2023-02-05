package org.includejoe.markety.feature_authentication.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.feature_authentication.data.remote.dto.LoginDTO
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
    ): Flow<Response<LoginDTO>> = flow {
        try {
            emit(Response.Loading<LoginDTO>())
            val data = repository.login(username, password)
            emit(Response.Success<LoginDTO>(data))
        } catch (e: HttpException) {
            val errorString = e.response()?.errorBody()?.string()
            when(e.code()) {
                400 -> {
                    emit(Response.Error<LoginDTO>(R.string.invalid_credentials))
                }
                else -> {
                    emit(Response.Error<LoginDTO>(R.string.unexpected_error))
                }
            }
        } catch(e: IOException) {
            emit(Response.Error<LoginDTO>(R.string.internet_error))
        }
    }
}