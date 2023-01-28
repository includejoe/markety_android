package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.feature_authentication.presentation.RegisterViewModel

@Composable
fun SuccessDisplay(
    navController: NavController,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.success_illus),
            contentDescription = "successful registration illustration",
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = modifier.height(MaterialTheme.spacing.sm))

        Text(
            text = stringResource(id = R.string.successful_registration),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onBackground
        )

        Spacer(modifier = modifier.height(MaterialTheme.spacing.sm))

        TextButton(onClick = {
            navController.navigate(Screens.LoginScreen.route) {
                popUpTo(Screens.LoginScreen.route) {
                    inclusive = true
                }
            }
        }) {
            Text(
                text = stringResource(id = R.string.login_btn),
                color = MaterialTheme.colors.secondary,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}