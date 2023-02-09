package org.includejoe.markety.feature_comment.data.remote.dto

import com.google.gson.annotations.SerializedName
import org.includejoe.markety.base.domain.model.UserInfo

data class CommentDTO(
    val body: String,
    @SerializedName("created_at")
    val createdAt: String,
    val id: String,
    @SerializedName("is_reply")
    val isReply: Boolean,
    val likes: List<String>,
    val post: String,
    val replies: List<CommentDTO>,
    val user: UserInfo
)