package org.includejoe.markety.feature_user.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.includejoe.markety.base.presentation.composables.BottomNavigation
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.Avatar
import org.includejoe.markety.base.presentation.composables.NavigationItem
import org.includejoe.markety.base.presentation.composables.ServerError
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_user.presentation.composables.*

@Composable
fun LoggedInUserProfileScreen(
    navController: NavHostController,
    viewModel: LoggedInUserViewModel = hiltViewModel()
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
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .weight(1f)
        ) {
            LoggedInUserProfileTopBar(navController)

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

                FollowOrEditButton(text = R.string.edit_btn) {}
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
                        R.string.reviews,
                        R.string.saved,
                    ) else listOf(
                        R.string.posts,
                        R.string.saved
                    )
                ) { selectedTabIndex = it }
                when(selectedTabIndex) {
                    0 -> {
                        PostsTabView(
                            isLoading = state.value.userPostsLoading,
                            posts = state.value.userPosts,
                            navController = navController
                        )
                    }
                    1 -> {
                        if(state.value.data!!.isVendor == true) {
                            ReviewsTabView()
                        } else {
                            SavedTabView()
                        }

                    }
                    2 -> {
                        SavedTabView()
                    }
                }
            }
        }

        BottomNavigation(
            selectedItem = NavigationItem.PROFILE,
            navController = navController
        )
    }
}