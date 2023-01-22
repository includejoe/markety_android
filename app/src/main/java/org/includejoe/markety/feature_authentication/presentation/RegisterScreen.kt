package org.includejoe.markety.feature_authentication.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.MButton
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.feature_authentication.presentation.composables.TextInput
import org.includejoe.markety.feature_authentication.util.InputType
import org.includejoe.markety.feature_authentication.util.LoginEvent

@Composable
fun RegisterScreen(
    navController: NavController
) {
    Column(
        Modifier
            .padding(MaterialTheme.spacing.md)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.sign_up_illus),
            contentDescription = "sign_up_illus",
            modifier = Modifier
                .scale(0.8f)
                .padding(8.dp)
        )

        Divider(
            color = MaterialTheme.colors.surface.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 48.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(stringResource(R.string.yes_account), color = MaterialTheme.colors.onBackground)
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))
            TextButton(
                onClick = {
                    navController.navigate(Screens.LoginScreen.route) {
                        popUpTo(Screens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                },
            ) {
                Text(text = stringResource(R.string.login_btn), color = MaterialTheme.colors.secondary)
            }
        }
    }
    
}