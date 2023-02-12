package org.includejoe.markety.feature_comment.data.repository

import org.includejoe.markety.base.data.remote.MarketyAPI
import org.includejoe.markety.feature_comment.data.remote.dto.CommentDTO
import org.includejoe.markety.feature_comment.domain.models.Comment
import org.includejoe.markety.feature_comment.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val api: MarketyAPI
): CommentRepository {
    override suspend fun createComment(
        authHeader: String,
        postId: String,
        body: String
    ): CommentDTO {
        return api.createComment(
            authHeader = authHeader,
            body = Comment (
                post = postId,
                body = body
            )
        )
    }

    override suspend fun getPostComments(
        authHeader: String,
        postId: String
    ): List<CommentDTO> {
        return api.getPostComments(
            authHeader = authHeader,
            postId = postId
        )
    }

    override suspend fun replyComment(
        authHeader: String,
        commentId: String,
        postId: String,
        body: String
    ): CommentDTO {
        return api.replyComment(
            authHeader = authHeader,
            commentId = commentId,
            body = Comment (
                post = postId,
                body = body
            )
        )
    }

    override suspend fun likeComment(
        authHeader: String,
        commentId: String
    ): CommentDTO {
        return api.likeComment(
            authHeader = authHeader,
            commentId = commentId
        )
    }

    override suspend fun deleteComment(
        authHeader: String,
        commentId: String
    ) {
        return api.deleteComment(
            authHeader = authHeader,
            commentId = commentId
        )
    }
}