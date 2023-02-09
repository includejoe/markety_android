package org.includejoe.markety.feature_comment.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.feature_comment.data.remote.dto.CommentDTO
import org.includejoe.markety.feature_comment.domain.repository.CommentRepository
import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPostCommentsUseCase @Inject constructor(
    private val repository: CommentRepository
) {
    operator fun invoke(
        jwt: String?,
        postId: String
    ): Flow<Response<List<CommentDTO>>> = flow {
        try {
            emit(Response.Loading<List<CommentDTO>>())
            val data = repository.getPostComments(
                authHeader = "Bearer $jwt",
                postId = postId
            )
            emit(Response.Success<List<CommentDTO>>(data))
        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()?.string()
            emit(Response.Error<List<CommentDTO>>(R.string.unexpected_error))
        } catch (e: IOException) {
            emit(Response.Error<List<CommentDTO>>(R.string.internet_error))
        }
    }
}