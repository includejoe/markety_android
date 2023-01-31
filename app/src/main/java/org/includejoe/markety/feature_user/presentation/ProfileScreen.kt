package org.includejoe.markety.feature_user.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.first
//import org.includejoe.markety.base.MainViewModel
import org.includejoe.markety.base.presentation.composables.AppTopBar
import org.includejoe.markety.base.presentation.composables.BottomNavigation
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.base.util.NavigationItem
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.feature_user.presentation.composables.ProfileTopBar

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: UserViewModel = hiltViewModel()
){
    val state = viewModel.state

    if(!state.value.getLoggedInUserSuccess) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(25.dp),
                color = MaterialTheme.colors.primary,
                strokeWidth = 2.dp
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ProfileTopBar(navController = navController, username = state.value.data?.username!! )
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
                    onClick = {
                        viewModel.toggleTheme()
                    },
                    modifier = Modifier.background(MaterialTheme.colors.secondary)
                ) {
                    Text(text = "Toggle Theme", color = MaterialTheme.colors.onBackground)
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))
                TextButton(
                    onClick = {
                        viewModel.logOut()
                        navController.navigate(Screens.LoginScreen.route) {
                            popUpTo(Screens.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier.background(MaterialTheme.colors.primary)
                ) {
                    Text(text = "Sign Out", color = MaterialTheme.colors.onBackground)
                }
            }
            BottomNavigation(
                selectedItem = NavigationItem.PROFILE,
                navController = navController
            )
        }
    }
}