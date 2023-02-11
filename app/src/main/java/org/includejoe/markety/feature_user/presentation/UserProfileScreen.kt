package org.includejoe.markety.feature_user.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.Avatar
import org.includejoe.markety.base.presentation.composables.ServerError
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_user.presentation.composables.*

@Composable
fun UserProfileScreen(
    navController: NavHostController,
    viewModel: UserViewModel = hiltViewModel()
){
    val state = viewModel.state
    var selectedTabIndex by remember { mutableStateOf(0) }
    val constraints = ConstraintSet {
        val avatar = createRefFor("avatar")
        val coverImage = createRefFor("coverImage")
        val followOrEditBtn = createRefFor("followOrEditBtn")

        constrain(avatar) {
            top.linkTo(coverImage.bottom)
            bottom.linkTo(coverImage.bottom)
            start.linkTo(parent.start)
        }

        constrain(coverImage) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(followOrEditBtn) {
            top.linkTo(coverImage.bottom)
            end.linkTo(parent.end)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if(state.value.loading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(25.dp),
                    color = MaterialTheme.colors.primary,
                    strokeWidth = 2.dp
                )
            }
        } else if(state.value.error !== null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = MaterialTheme.spacing.sm)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ServerError(error = state.value.error!!, toast = false)

//                TextButton(onClick = {
//                    viewModel.getUser()
//                }) {
//                    Text(
//                        text = stringResource(id = R.string.try_again_btn),
//                        color = MaterialTheme.colors.secondary,
//                        style = MaterialTheme.typography.button
//                    )
//                }
            }
        } else {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)) {
                    UserProfileTopBar(
                        navController = navController,
                        username = state.value.data?.username!!
                    )

                    ConstraintLayout(
                        constraintSet = constraints,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                    ) {
                        CoverImage(
                            src = state.value.data!!.coverImage
                        )

                        Avatar(
                            modifier = Modifier
                                .padding(start = MaterialTheme.spacing.sm)
                                .size(80.dp),
                            src = state.value.data!!.profileImage,
                            isDarkTheme = viewModel.baseApp.isDarkTheme.value
                        )

                        // TODO: Dynamic follow or unfollow
                        FollowOrEditButton(text = R.string.follow_btn) {}
                    }

                    Spacer(modifier = Modifier.height(46.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = MaterialTheme.spacing.sm)
                            .weight(1f)
                    ) {
                        Details(state = state, isVendor = state.value.data?.isVendor!!)
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))
                        TabView(
                            tabModels = if(state.value.data!!.isVendor == true) listOf(
                                R.string.posts,
                                R.string.reviews
                            ) else listOf(
                                R.string.posts,
                            )
                        ) { selectedTabIndex = it }
                        when(selectedTabIndex) {
                            0 -> {
                                PostsTabView(
                                    isLoading = state.value.userPostsLoading,
                                    posts = state.value.userPosts
                                )
                            }
                            1 -> {
                                ReviewsTabView()
                            }
                        }
                    }
                }
            }
    }
}