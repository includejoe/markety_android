package org.includejoe.markety.base.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.includejoe.markety.R
import org.includejoe.markety.base.util.NavigationItem

@Composable
fun AppTopBar(
    navController: NavController
) {
    val currentRoute = navController.currentDestination?.route
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp)
            .height(50.dp)
            .background(MaterialTheme.colors.primaryVariant),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.text_logo_primary),
            contentDescription = "logo",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(start = 30.dp)
                .scale(2f)
        )

        Icon(
            imageVector = if(currentRoute == NavigationItem.MESSAGES.route) NavigationItem.MESSAGES.isSelectedIcon
            else NavigationItem.MESSAGES.icon,
            contentDescription = stringResource(id = NavigationItem.MESSAGES.title),
            modifier = Modifier
                .padding(end = 15.dp)
                .size(23.dp)
                .clickable {
                    navController.navigate(NavigationItem.MESSAGES.route)
                },
            tint = if(currentRoute == NavigationItem.MESSAGES.route) MaterialTheme.colors.primary
            else MaterialTheme.colors.onBackground
        )
    }
}