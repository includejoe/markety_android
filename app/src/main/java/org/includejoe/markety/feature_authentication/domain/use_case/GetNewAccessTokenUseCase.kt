package org.includejoe.markety.feature_authentication.domain.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_authentication.data.remote.dto.TokensDTO
import org.includejoe.markety.feature_authentication.domain.model.RefreshTokenResponse
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewAccessTokenUseCase @Inject constructor(
    private val repository: AuthenticationRepository,
    private val tokenManager: TokenManager
) {
    operator fun invoke(refreshToken: String): Flow<Response<RefreshTokenResponse>> = flow {
        try {
            emit(Response.Loading<RefreshTokenResponse>())
            val data = repository.getNewAccessToken(tokenManager.readRefreshToken()!!)
            emit(Response.Success<RefreshTokenResponse>(data))
        } catch (e: HttpException){
            val errorMessage = e.response()?.errorBody()?.string()
            Log.d("http_error", errorMessage!!)
            emit(Response.Error<RefreshTokenResponse>(R.string.unexpected_error))
        } catch(e: IOException) {
            emit(Response.Error<RefreshTokenResponse>(R.string.internet_error))
        }
    }
}