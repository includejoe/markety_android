package org.includejoe.markety.base.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.theme.ui.DarkGray
import org.includejoe.markety.base.presentation.theme.ui.spacing

@Composable
fun ConfirmationDialog(
    text: Int,
    yes: () -> Unit,
    no: () -> Unit
) {
    Dialog(onDismissRequest = no) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 40.dp)
                    .background(
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colors.background
                    )
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))

                CButton(text = R.string.yes_btn, yes = true) { yes() }
                CButton(text = R.string.no_btn) { no() }
            }
        }
    }
}

@Composable
fun CButton(
    modifier: Modifier = Modifier,
    text: Int,
    yes: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(0.65f)
            .clip(MaterialTheme.shapes.small),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if(yes) MaterialTheme.colors.primary else Color.Transparent,
            disabledBackgroundColor = MaterialTheme.colors.primary,
        ),
        elevation = null,
        enabled = enabled
    ) {
        Text(
            text = stringResource(id = text),
            color = if(yes) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )
    }
}