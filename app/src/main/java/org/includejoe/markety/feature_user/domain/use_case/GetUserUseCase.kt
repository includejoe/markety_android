package org.includejoe.markety.feature_user.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.feature_user.data.remote.dto.UserDTO
import org.includejoe.markety.feature_user.domain.repository.UserRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(jwt: String?, username: String): Flow<Response<UserDTO>> = flow {
        try {
            emit(Response.Loading<UserDTO>())
            val data = repository.getUser(
                authHeader = "Bearer $jwt",
                username = username
            )
            emit(Response.Success<UserDTO>(data))
        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()?.string()
            emit(Response.Error<UserDTO>(R.string.unexpected_error))
        } catch (e: IOException) {
            emit(Response.Error<UserDTO>(R.string.internet_error))
        }
    }
}