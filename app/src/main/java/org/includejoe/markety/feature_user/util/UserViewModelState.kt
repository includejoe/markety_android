package org.includejoe.markety.feature_user.util

import org.includejoe.markety.feature_user.domain.model.User

data class UserViewModelState(
    var isLoading: Boolean = false,
    var getLoggedInUserSuccess: Boolean = false,
    val getUserLoggedInError: Any? = null,
    val data: User? = null
)
