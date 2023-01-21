package org.includejoe.markety.feature_authentication.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_authentication.data.remote.dto.TokensDTO
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import retrofit2.HttpException
import javax.inject.Inject

class GetNewAccessTokenUseCase @Inject constructor(
    private val repository: AuthenticationRepository,
    private val tokenManager: TokenManager
) {
    operator fun invoke(refreshToken: String): Flow<Response<TokensDTO>> = flow {
        try {
            emit(Response.Loading<TokensDTO>())
            val data = repository.getNewAccessToken(tokenManager.readRefreshToken()!!)
        } catch(e: HttpException) {

        }
    }
}