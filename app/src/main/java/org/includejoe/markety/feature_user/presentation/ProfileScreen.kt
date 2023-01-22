package org.includejoe.markety.feature_user.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.includejoe.markety.base.presentation.composables.AppTopBar
import org.includejoe.markety.base.presentation.composables.BottomNavigation
import org.includejoe.markety.base.util.NavigationItem

@Composable
fun ProfileScreen(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppTopBar(navController = navController)
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "PROFILE SCREEN", color = MaterialTheme.colors.onBackground)
        }
        BottomNavigation(
            selectedItem = NavigationItem.PROFILE,
            navController = navController
        )
    }
}