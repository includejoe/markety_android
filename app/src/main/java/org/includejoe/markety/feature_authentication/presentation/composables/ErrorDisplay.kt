package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun ErrorDisplay(
    navController: NavController,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.unsuccessful_illus),
            contentDescription = "unsuccessful registration illustration",
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = modifier.height(MaterialTheme.spacing.md))

        Text(
            text = stringResource(id = R.string.unsuccessful_registration),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body1
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
                text = stringResource(id = R.string.return_btn),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.button
            )
        }
    }
}