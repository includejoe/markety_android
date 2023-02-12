package org.includejoe.markety.feature_post.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.ServerError
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_comment.presentation.composables.Comment
import org.includejoe.markety.feature_post.presentation.composables.PostCard
import org.includejoe.markety.feature_post.presentation.composables.PostDetailTopBar

@Composable
fun PostDetailScreen(
    navController: NavHostController,
    viewModel: PostDetailViewModel = hiltViewModel()
){
    val state = viewModel.state

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
        } else if (state.value.error !== null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = MaterialTheme.spacing.sm)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ServerError(error = state.value.error!!, toast = false)
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
                    postName = state.value.post?.name!!
                )
                LazyColumn {
                    item {
                        PostCard(post = state.value.post!!, navController = navController)
                    }

                    if(state.value.comments !== null && state.value.comments!!.isNotEmpty()) {
                        item {
                            Text(
                                text = stringResource(id = R.string.comments),
                                style = MaterialTheme.typography.h2,
                                color = MaterialTheme.colors.onBackground,
                                modifier = Modifier.padding(start = MaterialTheme.spacing.sm)
                            )
                        }

                        items(state.value.comments!!) {comment ->
                            Comment(
                                isDarkTheme = viewModel.baseApp.isDarkTheme.value,
                                comment = comment,
                                replyingTo = if(comment.isReply) comment.ogCommentOwner
                                else null
                            )
                        }
                    }
                }
            }
        }
    }
}