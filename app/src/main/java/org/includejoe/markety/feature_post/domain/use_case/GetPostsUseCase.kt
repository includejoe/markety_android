package org.includejoe.markety.feature_post.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import org.includejoe.markety.feature_post.domain.repository.PostRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository,
) {
    operator fun invoke(jwt: String?): Flow<Response<List<PostDTO>>> = flow {
        try {
            emit(Response.Loading<List<PostDTO>>())
            val data = repository.getPosts(authHeader = "Bearer $jwt")
            emit(Response.Success<List<PostDTO>>(data))
        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()?.string()
            emit(Response.Error<List<PostDTO>>(R.string.unexpected_error))
        } catch(e: IOException) {
            emit(Response.Error<List<PostDTO>>(R.string.internet_error))
        }
    }
}