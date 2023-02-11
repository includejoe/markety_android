package org.includejoe.markety.base.presentation.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import java.text.SimpleDateFormat
import java.util.*
import org.includejoe.markety.R

@Composable
fun PostDateFormatter(dateString: String) {
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
                text = stringResource(id = R.string.secs_ago, diffInSeconds),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        }
        diffInMinutes < 60 ->  {
            Text(
                text = stringResource(id = R.string.mins_ago, diffInMinutes),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        }
        diffInHours < 24 ->  {
            Text(
                text = stringResource(id = R.string.hrs_ago, diffInHours),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        }
        diffInDays < 7 ->  {
            Text(
                text = stringResource(id = R.string.days_ago, diffInDays),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        }
        else -> {
            val calendar = Calendar.getInstance().apply { time = date }
            val fullDate = "${calendar.get(Calendar.DATE)}th ${calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)}, ${calendar.get(Calendar.YEAR)}"
            Text(
                text = stringResource(
                    id = R.string.full_date,
                    fullDate
                ),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.SemiBold
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