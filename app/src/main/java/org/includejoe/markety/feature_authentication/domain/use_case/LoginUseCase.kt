package org.includejoe.markety.feature_authentication.domain.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.UIText
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
            when(e.code().toString()) {
                "400" -> {
                    emit(Response.Error<Login>(R.string.invalid_credentials))
                }
                else -> {
                    emit(Response.Error<Login>(R.string.unexpected_error))
                }
            }
        } catch(e: IOException) {
            emit(Response.Error<Login>(R.string.internet_error))
        }
    }
}