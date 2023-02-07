package org.includejoe.markety.feature_post.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.theme.ui.spacing
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
                when(state.value.error) {
                    is Int -> {
                        Text(
                            text = stringResource(id = state.value.error as Int),
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body1
                        )
                    }
                    is String -> {
                        Text(
                            text = state.value.error as String,
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
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
                PostCard(post = state.value.post!!) {
                    
                }
            }
        }
    }
}
