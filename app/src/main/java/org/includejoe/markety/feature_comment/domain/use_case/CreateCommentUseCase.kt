package org.includejoe.markety.feature_comment.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.feature_comment.data.remote.dto.CommentDTO
import org.includejoe.markety.feature_comment.domain.repository.CommentRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateCommentUseCase @Inject constructor(
    private val repository: CommentRepository
) {
    operator fun invoke(
        jwt: String?,
        postId: String,
        body: String
    ): Flow<Response<CommentDTO>> = flow {
        try {
            emit(Response.Loading<CommentDTO>())
            val data = repository.createComment(
                authHeader = "Bearer $jwt",
                postId = postId,
                body = body
            )
            emit(Response.Success<CommentDTO>(data))
        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()?.string()
            emit(Response.Error<CommentDTO>(R.string.unexpected_error))
        } catch (e: IOException) {
            emit(Response.Error<CommentDTO>(R.string.internet_error))
        }
    }
}