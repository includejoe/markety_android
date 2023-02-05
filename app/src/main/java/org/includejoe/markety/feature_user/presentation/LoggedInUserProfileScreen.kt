package org.includejoe.markety.feature_user.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import org.includejoe.markety.base.presentation.composables.BottomNavigation
import org.includejoe.markety.base.util.NavigationItem
import org.includejoe.markety.feature_user.presentation.composables.ProfileTopBar
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.Avatar
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_user.presentation.composables.CoverImage
import org.includejoe.markety.feature_user.presentation.composables.Details
import org.includejoe.markety.feature_user.presentation.composables.FollowOrEditButton
import org.includejoe.markety.feature_user.util.UserViewModelState
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: LoggedInUserViewModel = hiltViewModel()
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

    Column(
        modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        if(state.value.isLoading) {
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
        } else if(state.value.getUserLoggedInError !== null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = MaterialTheme.spacing.sm)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when(state.value.getUserLoggedInError) {
                    is Int -> {
                        Text(
                            text = stringResource(id = state.value.getUserLoggedInError as Int),
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body1
                        )
                    }
                    is String -> {
                        Text(
                            text = state.value.getUserLoggedInError as String,
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
                TextButton(onClick = {
                    viewModel.getLoggedInUser()
                }) {
                    Text(
                        text = stringResource(id = R.string.try_again_btn),
                        color = MaterialTheme.colors.secondary,
                        style = MaterialTheme.typography.button
                    )
                }
            }
        } else {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)) {
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
                            src = state.value.data!!.profileImage,
                            isDarkTheme = viewModel.baseApp.isDarkTheme.value
                        )

                        if(viewModel.baseApp.loggedInUser.value == state.value.data!!.username) {
                            FollowOrEditButton(text = R.string.edit_btn) {}
                        } else {
                            FollowOrEditButton(text = R.string.follow_btn) {}
                        }

                    }

                    Spacer(modifier = Modifier.height(46.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = MaterialTheme.spacing.sm)
                            .weight(1f)
                    ) {
                        Details(state = state, isVendor = state.value.data?.isVendor!!)
                    }
                }
            }

        BottomNavigation(
            selectedItem = NavigationItem.PROFILE,
            navController = navController
        )
    }
}