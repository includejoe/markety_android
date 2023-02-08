package org.includejoe.markety.feature_comment.domain.repository

import org.includejoe.markety.feature_comment.data.remote.dto.CommentDTO

interface CommentRepository {
    suspend fun createComment(
        authHeader: String,
        postId: String,
        body: String,
    ): CommentDTO

    suspend fun getPostComments(
        authHeader: String,
        postId: String
    ): List<CommentDTO>

    suspend fun replyComment(
        authHeader: String,
        commentId: String,
        postId: String,
        body: String,
    ): CommentDTO

    suspend fun likeComment(
        authHeader: String,
        commentId: String,
    ): CommentDTO

    suspend fun deleteComment(
        authHeader: String,
        commentId: String,
    )
}