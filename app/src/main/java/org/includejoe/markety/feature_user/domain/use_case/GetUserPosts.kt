package org.includejoe.markety.feature_user.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import org.includejoe.markety.feature_user.domain.repository.UserRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserPosts @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(jwt: String?, username: String): Flow<Response<List<PostDTO>>> = flow {
        try {
            emit(Response.Loading<List<PostDTO>>())
            val data = repository.getUserPosts(
                authHeader = "Bearer $jwt",
                username = username
            )
            emit(Response.Success<List<PostDTO>>(data))
        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()?.string()
            emit(Response.Error<List<PostDTO>>(R.string.unexpected_error))
        } catch (e: IOException) {
            emit(Response.Error<List<PostDTO>>(R.string.internet_error))
        }
    }
}