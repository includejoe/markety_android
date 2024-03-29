package org.includejoe.markety.feature_authentication.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsWithImePadding
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.MButton
import org.includejoe.markety.base.presentation.composables.ServerError
import org.includejoe.markety.feature_authentication.presentation.composables.PasswordInput
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.feature_authentication.presentation.composables.CustomDivider
import org.includejoe.markety.feature_authentication.presentation.composables.ErrorText
import org.includejoe.markety.feature_authentication.presentation.composables.TextInput
import org.includejoe.markety.feature_authentication.util.InputType
import org.includejoe.markety.feature_authentication.util.FormEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
){
    val state = viewModel.state
    val passwordFR = FocusRequester()
    val focusManager = LocalFocusManager.current
    // Experimental API
    val keyBoardController = LocalSoftwareKeyboardController.current

    if(state.value.submissionError != null) {
        ServerError(error = state.value.submissionError!!, toast = true)
    }


    if(state.value.submissionSuccess) {
        LaunchedEffect(key1 = true) {
            navController.navigate(Screens.HomeScreen.route) {
                popUpTo(Screens.LoginScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    Column(
        Modifier
            .navigationBarsWithImePadding()
            .padding(MaterialTheme.spacing.md)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_illus),
            contentDescription = "login illustration",
            modifier = Modifier.size(250.dp)
        )

        Text(
            text = "LOGIN",
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.h1,
        )

        TextInput(
            value = state.value.username,
            error = state.value.usernameError,
            onValueChange = { viewModel.onEvent(FormEvent.UsernameChanged(it)) },
            inputType = InputType.Username,
            keyboardActions = KeyboardActions(onNext = {
                passwordFR.requestFocus()
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        if (state.value.usernameError != null) {
            ErrorText(text = state.value.usernameError!!)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        PasswordInput(
            value = state.value.password,
            error = state.value.passwordError,
            onValueChange = { viewModel.onEvent(FormEvent.PasswordChanged(it)) },
            inputType = InputType.Password,
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            focusRequester = passwordFR,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            )
        )

        if (state.value.passwordError != null) {
            ErrorText(text = state.value.passwordError!!)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        if (state.value.isSubmitting) {
            CircularProgressIndicator(
                modifier = Modifier.size(25.dp),
                color = MaterialTheme.colors.primary
            )
        } else {
            MButton(
                onClick = {
                    viewModel.onEvent(FormEvent.Login)
                    keyBoardController?.hide()
                },
                text = stringResource(id = R.string.login_btn)
            )
        }

        CustomDivider()

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(R.string.no_account),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body1,
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.sm))
            Text(
                text = stringResource(R.string.register),
                color = MaterialTheme.colors.secondary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.clickable {
                    keyBoardController?.hide()
                    navController.navigate(Screens.RegisterScreen.route) {
                        popUpTo(Screens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}