package org.includejoe.markety.feature_post.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.BottomNavigation
import org.includejoe.markety.base.presentation.composables.NavigationItem
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.feature_post.presentation.composables.HomeTopBar
import org.includejoe.markety.feature_post.presentation.composables.PostCard

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
){
    val state  = viewModel.state
//    val posts by rememberSaveable { mutableStateOf(viewModel.state.value.posts) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.value.postsLoading)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HomeTopBar(navController = navController)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.value.postsLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(25.dp),
                        color = MaterialTheme.colors.primary,
                        strokeWidth = 2.dp
                    )
                }
            } else if (state.value.posts.isNullOrEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.no_posts),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1
                    )
                }
            } else {
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        viewModel.getPosts()
                    },
                    indicator = { state, refreshTrigger ->
                        SwipeRefreshIndicator(
                            state = state,
                            refreshTriggerDistance = refreshTrigger,
                            backgroundColor = MaterialTheme.colors.background,
                            contentColor = MaterialTheme.colors.primary,
                        )
                    }
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.value.posts!!) { post ->
                            PostCard(post = post, navController = navController) {
                                navController.navigate(Screens.PostDetailScreen.route + "/${post.id}")
                            }
                        }
                    }
                }
            }
        }
        BottomNavigation(
            selectedItem = NavigationItem.HOME,
            navController = navController
        )
    }

}