package org.includejoe.markety.feature_post.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import org.includejoe.markety.feature_post.domain.repository.PostRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LikePostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke(jwt: String?, postId: String): Flow<Response<PostDTO>> = flow {
        try {
            emit(Response.Loading<PostDTO>())
            val data = repository.likePost(authHeader = "Bearer $jwt", postId = postId)
            emit(Response.Success<PostDTO>(data))
        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()?.string()
            emit(Response.Error<PostDTO>(R.string.unexpected_error))
        } catch(e: IOException) {
            emit(Response.Error<PostDTO>(R.string.internet_error))
        }
    }
}