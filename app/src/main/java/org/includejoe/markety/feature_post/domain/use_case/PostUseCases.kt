package org.includejoe.markety.feature_post.domain.use_case

data class PostUseCases(
    val getPosts: GetPostsUseCase,
    val getPost: GetPostUseCase,
    val likePost: LikePostUseCase
)
