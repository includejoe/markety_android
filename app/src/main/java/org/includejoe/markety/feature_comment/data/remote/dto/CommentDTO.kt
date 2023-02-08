package org.includejoe.markety.feature_comment.data.remote.dto

import org.includejoe.markety.base.domain.model.UserInfo

data class CommentDTO(
    val body: String,
    val created_at: String,
    val id: String,
    val is_reply: Boolean,
    val likes: List<Any>,
    val post: String,
    val replies: List<CommentDTO>,
    val user: UserInfo
)