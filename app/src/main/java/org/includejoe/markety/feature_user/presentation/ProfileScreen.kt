package org.includejoe.markety.feature_user.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import org.includejoe.markety.base.presentation.composables.Avatar
import org.includejoe.markety.base.presentation.composables.BottomNavigation
import org.includejoe.markety.base.util.NavigationItem
import org.includejoe.markety.feature_user.presentation.composables.ProfileTopBar
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.theme.ui.spacing

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: UserViewModel = hiltViewModel()
){
    val state = viewModel.state
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

    Column(modifier = Modifier.fillMaxSize()) {
        if(!state.value.getLoggedInUserSuccess) {
            Column(
                modifier = Modifier.fillMaxSize().weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(25.dp),
                    color = MaterialTheme.colors.primary,
                    strokeWidth = 2.dp
                )
            }
        } else if(state.value.getUserLoggedInError !== null) {
            Column(
                modifier = Modifier.fillMaxSize().weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when(state.value.getUserLoggedInError) {
                    is Int -> {
                        Text(
                            text = stringResource(id = state.value.getUserLoggedInError as Int),
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    is String -> {
                        Text(
                            text = state.value.getUserLoggedInError as String,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        } else {
                Column(modifier = Modifier.fillMaxSize().weight(1f)) {
                    ProfileTopBar(
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
                            src = state.value.data!!.profileImage
                        )
                        FollowOrEditButton(follow = false) {

                        }
                    }

                    Spacer(modifier = Modifier.height(46.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    ) {

                    }
                }
            }

        BottomNavigation(
            selectedItem = NavigationItem.PROFILE,
            navController = navController
        )
    }
}

@Composable
private fun CoverImage(src: String? = null) {
    Image(
        painter = if(src !== null) rememberAsyncImagePainter(src)
        else painterResource(id = R.drawable.cover_photo_placeholder),
        modifier = Modifier
            .fillMaxSize()
            .layoutId("coverImage"),
        contentDescription = "coverImage",
        contentScale = ContentScale.Crop
    )
}


@Composable
private fun FollowOrEditButton(
    follow: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(end = MaterialTheme.spacing.sm, top = MaterialTheme.spacing.sm)
            .height(30.dp)
            .layoutId("followOrEditBtn"),
        shape = MaterialTheme.shapes.medium,
    ) {
        if(follow) {
            Text(text = stringResource(id = R.string.follow_btn), fontSize = 12.sp)
        } else {
            Text(text = stringResource(id = R.string.edit_btn), fontSize = 12.sp)
        }
    }
}