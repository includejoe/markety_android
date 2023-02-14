package org.includejoe.markety.feature_comment.util

import org.includejoe.markety.feature_comment.data.remote.dto.CommentDTO

data class CommentState(
    val loading: Boolean = false,
    val data: CommentDTO? = null,
    val error: Any? = null
)
