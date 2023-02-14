package org.includejoe.markety.base.presentation.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import java.text.SimpleDateFormat
import java.util.*
import org.includejoe.markety.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DateFormatter(
    dateString: String,
    fontWeight: FontWeight = FontWeight.SemiBold,
) {
    val date = parseDate(dateString)

    val now = Calendar.getInstance().time
    val diffInMilliseconds = now.time - date?.time!!
    val diffInSeconds = diffInMilliseconds / 1000
    val diffInMinutes = diffInSeconds / 60
    val diffInHours = diffInMinutes / 60
    val diffInDays = diffInHours / 24

    when {
        diffInSeconds < 60 ->  {
            Text(
                text = pluralStringResource(
                    id = R.plurals.second,
                    count = if(diffInSeconds == 1L) 1 else 2,
                    diffInSeconds
                ),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = fontWeight
            )
        }
        diffInMinutes < 60 ->  {
            Text(
                text = pluralStringResource(
                    id = R.plurals.minute,
                    count = if(diffInMinutes == 1L) 1 else 2,
                    diffInMinutes
                ),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = fontWeight
            )
        }
        diffInHours < 24 ->  {
            Text(
                text = pluralStringResource(
                    id = R.plurals.hour,
                    count = if(diffInHours == 1L) 1 else 2,
                    diffInHours
                ),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = fontWeight
            )
        }
        diffInDays < 7 ->  {
            Text(
                text = pluralStringResource(
                    id = R.plurals.day,
                    count = if(diffInDays == 1L) 1 else 2,
                    diffInDays
                ),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = fontWeight
            )
        }
        else -> {
            val calendar = Calendar.getInstance().apply { time = date }
            val fullDate = "${calendar.get(Calendar.DATE)}th ${calendar.getDisplayName(
                Calendar.MONTH, 
                Calendar.SHORT, 
                Locale.ENGLISH)
            }, ${calendar.get(Calendar.YEAR)}"
            Text(
                text = stringResource(
                    id = R.string.full_date,
                    fullDate
                ),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = fontWeight
            )

        }
    }
}

private fun parseDate(dateString: String): Date? {
    val format: SimpleDateFormat = if(dateString.length > 24) {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.US)
    } else {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    }
    format.timeZone = TimeZone.getTimeZone("UTC")
    return format.parse(dateString)
}