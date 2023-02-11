package org.includejoe.markety.feature_messaging.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.includejoe.markety.feature_messaging.presentation.composables.MessagesTopBar

@Composable
fun MessagesScreen(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MessagesTopBar(navController = navController)
        Column(
            modifier = Modifier.weight(1f).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MESSAGES SCREEN",
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h2,
            )
        }
    }
}