package org.includejoe.markety.feature_user.domain.use_case

data class UserUseCases (
    val getLoggedInUser: GetLoggedInUserUseCase,
    val getUser: GetUserUseCase,
    val getUserPosts: GetUserPosts
)