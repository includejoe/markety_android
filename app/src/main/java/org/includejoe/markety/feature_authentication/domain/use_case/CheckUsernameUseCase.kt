package org.includejoe.markety.feature_authentication.domain.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.feature_authentication.data.remote.dto.CheckUsernameDTO
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CheckUsernameUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
   operator fun invoke(
       username: String
   ): Flow<Response<CheckUsernameDTO>> = flow {
       try {
           emit(Response.Loading<CheckUsernameDTO>())
           val data = repository.checkUsername(username)
           emit(Response.Success<CheckUsernameDTO>(data))
       } catch (e: HttpException) {
           val errorMessage = e.response()?.errorBody()?.string()
           emit(Response.Error<CheckUsernameDTO>(R.string.unexpected_error))
       } catch(e: IOException) {
           emit(Response.Error<CheckUsernameDTO>(R.string.internet_error))
       }
   }
}