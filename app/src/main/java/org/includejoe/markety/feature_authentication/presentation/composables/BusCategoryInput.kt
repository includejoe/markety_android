package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.theme.ui.LightGray
import org.includejoe.markety.feature_authentication.util.InputType

@Composable
fun BusCategoryInput(
    value: String,
    error: Any?,
    onValueChange: (String) -> Unit,
    inputType: InputType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    Column(Modifier.fillMaxWidth()) {
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            isError = error != null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth()
                .height(50.dp)
                .clickable {
                    showDialog = true
                },
            leadingIcon = {
                Icon(
                    imageVector = inputType.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            },
            placeholder = {
                Text(
                    text = stringResource(inputType.label),
                    color = LightGray,
                    style = MaterialTheme.typography.body1
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor = MaterialTheme.colors.onSurface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.body1,
            singleLine = true,
            visualTransformation = visualTransformation,
            enabled = false
        )

        if(showDialog) {
            BusCategoryPickerDialog(onSelection = onValueChange) {
                showDialog = false
            }
        }
    }
}

@Composable
fun BusCategoryPickerDialog(
    onSelection: (String) -> Unit,
    dismiss: () -> Unit,
){
    val context = LocalContext.current

    val categories = context.resources.getStringArray(R.array.categories)

    Dialog(onDismissRequest = dismiss) {
        Box {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 40.dp)
                    .background(
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colors.background
                    )
            ) {
                for(category in categories.sorted()) {
                    item{
                        Text(
                            modifier = Modifier
                                .clickable {
                                    onSelection(category)
                                    dismiss()
                                }
                                .fillMaxWidth()
                                .padding(10.dp),
                            text = category,
                            style = MaterialTheme.typography.body1,
                        )
                    }
                }
            }
        }
    }
}