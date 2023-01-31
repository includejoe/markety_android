package org.includejoe.markety.feature_user.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.includejoe.markety.base.util.NavigationItem
import org.includejoe.markety.feature_user.domain.model.User
import org.includejoe.markety.feature_user.util.UserViewModelState

@Composable
fun ProfileTopBar(
    navController: NavController,
    username: String = ""
) {
    val currentRoute = navController.currentDestination?.route
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp)
            .height(50.dp)
            .background(MaterialTheme.colors.primaryVariant)
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = username,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Icon(
            imageVector = if(currentRoute == NavigationItem.SETTINGS.route) NavigationItem.SETTINGS.isSelectedIcon
            else NavigationItem.SETTINGS.icon,
            contentDescription = stringResource(id = NavigationItem.SETTINGS.title),
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    navController.navigate(NavigationItem.SETTINGS.route)
                },
            tint = if(currentRoute == NavigationItem.SETTINGS.route) MaterialTheme.colors.primary
            else MaterialTheme.colors.onBackground
        )
    }
}