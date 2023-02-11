package org.includejoe.markety.feature_user.util

import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import org.includejoe.markety.feature_user.domain.model.User

data class UserState(
    var loading: Boolean = false,
    var success: Boolean = false,
    val error: Any? = null,
    val data: User? = null,

    val userPosts: List<PostDTO>? = null,
    val userPostsError: Any ? = null,
    val userPostsLoading: Boolean = false,
)
