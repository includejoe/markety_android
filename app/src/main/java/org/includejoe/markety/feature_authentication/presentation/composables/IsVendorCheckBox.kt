package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.includejoe.markety.feature_authentication.presentation.RegisterViewModel
import org.includejoe.markety.feature_authentication.util.FormEvent
import org.includejoe.markety.R

@Composable
fun IsVendorCheckBox(
    viewModel: RegisterViewModel
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = viewModel.state.value.isVendor,
            onCheckedChange = {
                viewModel.onEvent(FormEvent.IsVendorChanged(it))
            },
            colors = CheckboxDefaults.colors(
                uncheckedColor = MaterialTheme.colors.onBackground
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = stringResource(id = R.string.is_vendor))
    }
}