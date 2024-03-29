package org.includejoe.markety.feature_user.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Screens

@Composable
fun LoggedInUserProfileTopBar(
    navController: NavController,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp)
            .height(50.dp)
            .background(MaterialTheme.colors.primaryVariant)
            .padding(horizontal = MaterialTheme.spacing.sm),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.my_profile),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body1
        )

        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "settings",
            modifier = Modifier
                .size(26.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    navController.navigate(Screens.SettingsScreen.route)
                },
            tint = MaterialTheme.colors.onBackground
        )
    }
}