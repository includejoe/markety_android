package org.includejoe.markety.feature_authentication.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.MButton
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.feature_authentication.presentation.composables.TextInput
import org.includejoe.markety.feature_authentication.util.InputType

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
){
    val authValue = false
    val state = viewModel.state
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    if(authValue) {
        navController.navigate(Screens.FeedsScreen.route) {
            popUpTo(Screens.LoginScreen.route) {
                inclusive = true
            }
        }
    }

    Column(
        Modifier
            .background(MaterialTheme.colors.background)
            .padding(MaterialTheme.spacing.md)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.text_logo_primary),
            contentDescription = "logo",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier.scale(1.5f)
        )

        TextInput(
            inputType = InputType.Username,
            keyboardActions = KeyboardActions(onNext =    {
                passwordFocusRequester.requestFocus()
            })
        )

        TextInput(
            inputType = InputType.Password,
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            focusRequester = passwordFocusRequester
        )

        MButton(onClick = { /*TODO*/ }, text = R.string.login_btn)

        Divider(
            color = Color.White.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 48.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Don't have an account?", color = Color.White)
            TextButton(onClick = {}) {
                Text(text = "SIGN UP", color = MaterialTheme.colors.secondary)
            }
        }
    }
}