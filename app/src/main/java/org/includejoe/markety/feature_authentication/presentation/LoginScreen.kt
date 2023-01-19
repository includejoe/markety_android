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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.MButton
import org.includejoe.markety.base.presentation.composables.Toast
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.feature_authentication.domain.model.Login
import org.includejoe.markety.feature_authentication.presentation.composables.TextInput
import org.includejoe.markety.feature_authentication.util.InputType
import org.includejoe.markety.feature_authentication.util.LoginEvent

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
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.text_logo_primary),
            contentDescription = "logo",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier.scale(1.5f)
        )

        TextInput(
            value = state.value.username,
            error = state.value.usernameError,
            onValueChange = {viewModel.onEvent(LoginEvent.UsernameChanged(it))},
            inputType = InputType.Username,
            keyboardActions = KeyboardActions(onNext = {
                passwordFocusRequester.requestFocus()
            })
        )

        if(state.value.usernameError != null) {
            Text(
                text = stringResource(state.value.usernameError!!),
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End),
                fontSize = 12.sp
            )
        }
        
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        TextInput(
            value = state.value.password,
            error = state.value.passwordError,
            onValueChange = {viewModel.onEvent(LoginEvent.PasswordChanged(it))},
            inputType = InputType.Password,
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            focusRequester = passwordFocusRequester
        )

        if(state.value.passwordError != null) {
            Text(
                text = stringResource(state.value.passwordError!!),
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        if(state.value.isSubmitting) {
            CircularProgressIndicator(
                modifier = Modifier.size(25.dp),
                color = MaterialTheme.colors.primary
            )
        } else {
            MButton(
                onClick = { viewModel.onEvent(LoginEvent.Submit) },
                text = R.string.login_btn
            )
        }

        if(state.value.submissionError != null) {
            when(state.value.submissionError) {
                is Int -> {
                    Toast(message = stringResource(state.value.submissionError as Int))
                }

                is String -> {
                    Toast(message = state.value.submissionError as String)
                }
            }
        }


        Divider(
            color = MaterialTheme.colors.surface.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 48.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(R.string.no_account), color = MaterialTheme.colors.onBackground)
            TextButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = stringResource(R.string.register), color = MaterialTheme.colors.secondary)
            }
        }
    }
}