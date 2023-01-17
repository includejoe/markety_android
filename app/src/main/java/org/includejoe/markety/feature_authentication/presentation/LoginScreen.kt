package org.includejoe.markety.feature_authentication.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import org.includejoe.markety.base.util.Screens

@Composable
fun LoginScreen(navController: NavHostController){
    val authValue = false

    if(authValue) {
        navController.navigate(Screens.FeedsScreen.route) {
            popUpTo(Screens.LoginScreen.route) {
                inclusive = true
            }
        }
    }

    Text(text = "LOGIN SCREEN", color = Color.Black)
}