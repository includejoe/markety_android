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
import org.includejoe.markety.base.presentation.composables.Avatar
import org.includejoe.markety.base.presentation.composables.BottomNavigation
import org.includejoe.markety.base.util.NavigationItem
import org.includejoe.markety.feature_user.presentation.composables.ProfileTopBar
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_user.presentation.composables.FollowOrEditButton
import org.includejoe.markety.feature_user.util.UserViewModelState
import java.text.SimpleDateFormat
import java.util.*

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
                            textAlign = TextAlign.Center
                        )
                    }
                    is String -> {
                        Text(
                            text = state.value.getUserLoggedInError as String,
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                TextButton(onClick = {
                    viewModel.getLoggedInUser()
                }) {
                    Text(
                        text = stringResource(id = R.string.try_again_btn),
                        color = MaterialTheme.colors.secondary
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
                            src = state.value.data!!.profileImage
                        )

                        if(viewModel.appState.value.loggedInUser == state.value.data!!.username) {
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
private fun Details(
    isVendor: Boolean = false,
    state: State<UserViewModelState>
) {
    val iconSize = 15.dp

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        // First & Last Name or Business Name
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            if (isVendor) {
                Row(
                    modifier = Modifier.wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = state.value.data?.busName ?: "",
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.body1.fontSize
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_is_vendor),
                        modifier = Modifier.size(12.dp),
                        contentDescription = "location icon",
                    )
                }
            } else {
                Text(
                    text = "${state.value.data?.firstName} ${state.value.data?.lastName}",
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
            }
        }

        // Username
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "@${state.value.data?.username}",
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

        // Bio
        if (state.value.data?.bio !== null) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = state.value.data?.bio!!,
                    color = MaterialTheme.colors.onBackground,
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            // Location
            Row(
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    modifier = Modifier.size(iconSize),
                    contentDescription = "location icon",
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = state.value.data?.location ?: "",
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
            }

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))

            // Category
            if(isVendor) {
                Row(
                    modifier = Modifier.wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Category,
                        modifier = Modifier.size(iconSize),
                        contentDescription = "category icon",
                        tint = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = state.value.data?.busCategory ?: "",
                        fontSize = MaterialTheme.typography.body1.fontSize
                    )
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            // Business Website
            if(isVendor && state.value.data?.busWebsite !== null) {
                Row(
                    modifier = Modifier.wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Link,
                        modifier = Modifier.size(iconSize),
                        contentDescription = "website icon",
                        tint = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    BusWebsiteLink(link = state.value.data?.busWebsite!!)
                }
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))
            }

            // Joined Date
            Row(
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    modifier = Modifier.size(iconSize),
                    contentDescription = "joined icon",
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.width(3.dp))
                JoinedDate(dateString = state.value.data?.createdAt!!)
            }
        }

    }
}

@Composable
private fun JoinedDate(
    dateString: String
) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.UK)
    val outputFormat = SimpleDateFormat("MMMM yyyy", Locale.UK)
    val date = inputFormat.parse(dateString.slice(0..9))
    val formattedDate = outputFormat.format(date!!)

    Text(
        text = stringResource(id = R.string.joined_date, formattedDate),
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
private fun BusWebsiteLink(
    link: String
){
    val uriHandler = LocalUriHandler.current
    val annotatedString = buildAnnotatedString {
        append(link)

        val start = 0
        val end = link.length

        addStyle(
            SpanStyle(
                color = MaterialTheme.colors.secondary,
                textDecoration = TextDecoration.Underline,
                fontStyle = FontStyle.Italic,
                fontSize = 13.sp
            ),
            start = start,
            end = end
        )

        addStringAnnotation(
            tag ="busWebsite",
            annotation = link,
            start = start,
            end = end
        )
    }

    ClickableText(text = annotatedString) {
        uriHandler.openUri(link)
    }
}