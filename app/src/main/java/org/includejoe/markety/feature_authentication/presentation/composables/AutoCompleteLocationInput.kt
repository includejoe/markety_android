package org.includejoe.markety.feature_authentication.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.includejoe.markety.base.presentation.theme.ui.LightGray
import org.includejoe.markety.feature_authentication.presentation.RegisterViewModel
import org.includejoe.markety.feature_authentication.util.InputType

@Composable
fun AutoCompleteLocationInput(
    value: String,
    error: Any?,
    onValueChange: (String) -> Unit,
    inputType: InputType,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    viewModel: RegisterViewModel
){
    var expanded by remember {
        mutableStateOf(false)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = {
                expanded = false
            }
        )
    ) {
        TextField(
            value = value,
            onValueChange = {
                expanded = true
                onValueChange(it)
            },
            isError = error != null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth()
                .height(50.dp)
                .focusRequester(focusRequester ?: FocusRequester()),
            leadingIcon = {
                Icon(
                    imageVector = inputType.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            },
            trailingIcon = {
                // display no icon if predictions is empty
                if(viewModel.state.value.googlePlacesPredictions?.predictions.isNullOrEmpty()){
                    Box(){}
                }
                else if(viewModel.state.value.isGooglePlacesPredictionsLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.onSurface,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(10.dp)
                    )
                } else {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Rounded.KeyboardArrowUp
                            else Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "expand",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            },
            placeholder = {
                Text(
                    text = stringResource(inputType.label),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor = MaterialTheme.colors.onBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.body1,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            keyboardActions = keyboardActions
        )

        AnimatedVisibility(
            visible = expanded && !viewModel.state.value.isGooglePlacesPredictionsLoading
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colors.surface
                    ),
                elevation = 15.dp,
            ) {
                LazyColumn(
                    modifier = Modifier
                        .heightIn(max = 150.dp)
                        .background(MaterialTheme.colors.surface)
                ) {
                    val predictions = viewModel.state.value.googlePlacesPredictions?.predictions
                    if(!predictions.isNullOrEmpty()) {
                        items(predictions) {
                            Location(location = it.description) { location ->
                                expanded = false
                                onValueChange(location)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Location(
    location: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(location)
            }
            .padding(10.dp)
    ) {
        Text(
            text = location,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body1
        )
    }

}