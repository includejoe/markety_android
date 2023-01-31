package org.includejoe.markety.feature_user.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
//import org.includejoe.markety.base.MainViewModel
import org.includejoe.markety.base.presentation.composables.AppTopBar
import org.includejoe.markety.base.presentation.composables.BottomNavigation
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.base.util.NavigationItem

@Composable
fun ProfileScreen(
    navController: NavHostController,
//    mainViewModel: MainViewModel,
    viewModel: UserViewModel = hiltViewModel()
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppTopBar(navController = navController)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "PROFILE SCREEN", color = MaterialTheme.colors.onBackground)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))
            TextButton(
                onClick = { /* mainViewModel.toggleTheme() */ },
                modifier = Modifier.background(MaterialTheme.colors.secondary)
            ) {
                Text(text = "Toggle Theme", color = MaterialTheme.colors.onBackground)
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))
//            TextButton(
//                onClick = { viewModel.signOut() },
//                modifier = Modifier.background(MaterialTheme.colors.primary)
//            ) {
//                Text(text = "Sign Out", color = MaterialTheme.colors.onBackground)
//            }
        }
        BottomNavigation(
            selectedItem = NavigationItem.PROFILE,
            navController = navController
        )
    }
}