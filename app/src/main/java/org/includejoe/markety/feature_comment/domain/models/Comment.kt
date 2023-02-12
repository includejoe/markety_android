package org.includejoe.markety.feature_comment.domain.models

data class Comment(
    val post: String? = null,
    val commentId: String? = null,
    val body: String? = null
)