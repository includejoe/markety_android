package org.includejoe.markety.base.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.includejoe.markety.R

@Composable
fun BusName(
    name: String
) {
    Row(
        modifier = Modifier.wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = name,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.width(3.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_is_vendor),
            modifier = Modifier.size(12.dp),
            contentDescription = "location icon",
        )
    }
}