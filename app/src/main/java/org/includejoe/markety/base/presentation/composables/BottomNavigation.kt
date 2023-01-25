package org.includejoe.markety.base.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.includejoe.markety.base.util.NavigationItem

@Composable
fun BottomNavigation(
    selectedItem: NavigationItem,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(20.dp)
            .height(60.dp)
            .background(MaterialTheme.colors.primaryVariant),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val navItems = NavigationItem.values().dropLast(1)
        for (item in navItems) {
            Icon(
                imageVector = if(item == selectedItem) item.isSelectedIcon
                else item.icon,
                contentDescription = stringResource(id = item.title),
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .size(if(item == NavigationItem.CREATEPOST) 35.dp else 30.dp)
                    .size(30.dp)
                    .clickable {
                    navController.navigate(item.route)
                },
                tint = if(item == selectedItem) MaterialTheme.colors.primary
                else MaterialTheme.colors.onBackground
            )
        }
    }
}