package org.includejoe.markety.feature_user.util

import androidx.compose.runtime.State
import kotlinx.coroutines.flow.collectLatest
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.feature_user.domain.use_case.UserUseCases
import javax.inject.Inject

//class GetUserPostFunc @Inject constructor(
//    private val userUseCases: UserUseCases
//) {
//    suspend operator fun  invoke(
//        jwt: String,
//        username: String,
//        state: State<UserState>,
//    ) {
//        userUseCases.getUserPosts(jwt, username).collectLatest {result ->
//            when(result) {
//                is Response.Loading -> {
//                    state.value = state.value.copy(
//                        userPostsLoading = true
//                    )
//                }
//
//                is Response.Success -> {
//                    state.value = _state.value.copy(
//                        userPostsLoading = false,
//                        userPostsError = null,
//                        userPosts = result.data
//                    )
//                }
//
//                is Response.Error -> {
//                    state.value = state.value.copy(
//                        userPostsLoading = false,
//                        userPostsError = result.message ?: R.string.unexpected_error,
//                        userPosts = null
//                    )
//                }
//            }
//        }
//    }
//}