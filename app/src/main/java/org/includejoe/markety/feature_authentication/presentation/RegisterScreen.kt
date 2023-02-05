package org.includejoe.markety.feature_authentication.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import androidx.navigation.NavController
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.CountryPickerView
import org.includejoe.markety.base.presentation.composables.Toast
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.base.util.Screens
import org.includejoe.markety.feature_authentication.presentation.composables.*
import org.includejoe.markety.feature_authentication.util.FormEvent

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state = viewModel.state

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

    Column(
        Modifier
            .padding(MaterialTheme.spacing.md)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(state.value.currentDisplay in 2..4) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = "arrow-back",
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                    ) {
                        viewModel.onEvent(FormEvent.Previous)
                    }
                )
            }
        }

        Column(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(state.value.currentDisplay < 5) {
                Image(
                    painter = painterResource(id = R.drawable.sign_up_illus),
                    contentDescription = "signup illustration",
                    modifier = Modifier.size(250.dp)
                )

                Text(
                    text = "REGISTER",
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.h1
                )
            }

            when (state.value.currentDisplay) {
                1 -> RegisterFieldSet1(viewModel = viewModel)
                2 -> RegisterFieldSet2(viewModel = viewModel)
                3 -> RegisterFieldSet3(viewModel = viewModel)
                4 -> RegisterFieldSet4(viewModel = viewModel)
                5 -> {
                    when(state.value.submissionSuccess) {
                        true -> {
                            Box(modifier = Modifier.weight(1f)){
                                SuccessDisplay(navController)
                            }
                        }
                        false -> {
                            Box(modifier = Modifier.weight(1f)){
                                ErrorDisplay(navController)
                            }

                        }
                    }
                }
            }

            if(state.value.currentDisplay < 5) {
                CustomDivider()

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        stringResource(R.string.yes_account),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.sm))
                    Text(
                        text = stringResource(R.string.login_btn),
                        color = MaterialTheme.colors.secondary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.clickable {
                            navController.navigate(Screens.LoginScreen.route) {
                                popUpTo(Screens.LoginScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}