package org.includejoe.markety.feature_user.presentation.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.includejoe.markety.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun JoinedDate(
    dateString: String
) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.UK)
    val outputFormat = SimpleDateFormat("MMMM yyyy", Locale.UK)
    val date = inputFormat.parse(dateString.slice(0..9))
    val formattedDate = outputFormat.format(date!!)

    Text(
        text = stringResource(id = R.string.joined_date, formattedDate),
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.body1
    )
}