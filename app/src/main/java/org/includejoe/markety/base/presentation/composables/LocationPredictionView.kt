package org.includejoe.markety.base.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.includejoe.markety.base.domain.model.GooglePrediction
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_authentication.presentation.RegisterViewModel

@Composable
fun LocationPredictionView(
    expanded: Boolean = false,
    viewModel: RegisterViewModel
){
    AnimatedVisibility(visible = expanded) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.sm),
            elevation = 15.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            LazyColumn(
                modifier = Modifier.heightIn(max = 150.dp)
            ){
//                val predictions = viewModel.state.value.googlePlacesPredictions?.predictions
//                if(predictions!!.isNotEmpty()) {
//                    items(predictions) {
//                        Location(location = ) { location ->
//                            expanded = false
//                        }
//                    }
//                }
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
        Text(text = location, color = MaterialTheme.colors.onSurface)
    }

}