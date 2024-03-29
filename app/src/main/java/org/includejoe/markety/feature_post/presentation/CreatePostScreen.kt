package org.includejoe.markety.feature_post.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.includejoe.markety.base.presentation.composables.BottomNavigation
import org.includejoe.markety.base.presentation.composables.NavigationItem
import org.includejoe.markety.feature_post.presentation.composables.CreatePostTopBar

@Composable
fun CreatePostScreen(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CreatePostTopBar(navController = navController)
        Column(
            modifier = Modifier.weight(1f).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "CREATE POST SCREEN",
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h2,
            )
        }
        BottomNavigation(
            selectedItem = NavigationItem.CREATE_POST,
            navController = navController
        )
    }
}