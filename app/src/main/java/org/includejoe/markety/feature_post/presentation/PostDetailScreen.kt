package org.includejoe.markety.feature_post.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.BottomSheetMenu
import org.includejoe.markety.base.presentation.composables.ServerError
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.base.util.MenuItem
import org.includejoe.markety.feature_comment.presentation.composables.Comment
import org.includejoe.markety.feature_post.presentation.composables.PostCard
import org.includejoe.markety.feature_post.presentation.composables.PostDetailTopBar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostDetailScreen(
    navController: NavHostController,
    viewModel: PostDetailViewModel = hiltViewModel()
){
    val postState = viewModel.state
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {it != ModalBottomSheetValue.HalfExpanded},
        skipHalfExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    BackHandler(sheetState.isVisible) {
        coroutineScope.launch {
            sheetState.hide()
        }
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        sheetContent = {
            BottomSheetMenu(
                menuItems = listOf(
                    MenuItem(
                        icon = Icons.Default.Info,
                        label = stringResource(R.string.why_post),
                        onClick = {}
                    ),
                    MenuItem(
                        icon = Icons.Default.NotInterested,
                        label = stringResource(R.string.not_interested_post),
                        onClick = {}
                    ),
                    MenuItem(
                        icon = Icons.Default.Person,
                        label = stringResource(R.string.follow_user, "@${postState.value.post?.user?.username}"),
                        onClick = {}
                    ),
                    MenuItem(
                        icon = Icons.Default.Flag,
                        label = stringResource(id = R.string.report_post),
                        onClick = {}
                    ),
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            if (postState.value.isLoading) {
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
            } else if (postState.value.error !== null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = MaterialTheme.spacing.sm)
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ServerError(error = postState.value.error!!, toast = false)
                    TextButton(onClick = {

                    }) {
                        Text(
                            text = stringResource(id = R.string.try_again_btn),
                            color = MaterialTheme.colors.secondary,
                            style = MaterialTheme.typography.button
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PostDetailTopBar(
                        navController = navController,
                        postName = postState.value.post?.name!!
                    ) {
                        coroutineScope.launch {
                            if(sheetState.isVisible) {
                                sheetState.hide()
                            } else {
                                sheetState.animateTo(ModalBottomSheetValue.Expanded)
                            }
                        }
                    }
                    LazyColumn {
                        item {
                            PostCard(post = postState.value.post!!, navController = navController)
                        }

                        if (postState.value.comments !== null && postState.value.comments!!.isNotEmpty()) {
                            item {
                                Text(
                                    text = stringResource(id = R.string.comments),
                                    style = MaterialTheme.typography.h2,
                                    color = MaterialTheme.colors.onBackground,
                                    modifier = Modifier.padding(start = MaterialTheme.spacing.sm)
                                )
                            }

                            items(postState.value.comments!!) { comment ->
                                Comment(
                                    isDarkTheme = viewModel.baseApp.isDarkTheme.value,
                                    comment = comment,
                                    replyingTo = if (comment.isReply) comment.ogCommentOwner
                                    else null
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}