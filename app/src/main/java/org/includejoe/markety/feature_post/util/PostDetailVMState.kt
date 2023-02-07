package org.includejoe.markety.feature_post.util

import org.includejoe.markety.feature_post.data.remote.dto.PostDTO

data class PostDetailVMState(
    val isLoading: Boolean = false,
    val post: PostDTO? = null,
    val error: Any? = null
)