package org.includejoe.markety.feature_search.presentation

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
import org.includejoe.markety.feature_search.presentation.composables.SearchTopBar

@Composable
fun SearchScreen(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchTopBar(navController = navController)
        Column(
            modifier = Modifier.weight(1f).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SEARCH SCREEN",
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h2,
            )
        }
        BottomNavigation(
            selectedItem = NavigationItem.SEARCH,
            navController = navController
        )
    }
}