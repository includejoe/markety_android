package org.includejoe.markety.feature_post.util

import org.includejoe.markety.feature_post.data.remote.dto.PostDTO

data class HomeViewModelState(
    var postsLoading: Boolean = false,
    var posts: List<PostDTO>? = null,
    var getPostsError: Any? = null
)
