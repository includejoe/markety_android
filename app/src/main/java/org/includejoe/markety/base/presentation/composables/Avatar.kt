package org.includejoe.markety.base.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.includejoe.markety.R

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    src: String? = null
) {
    Image(
        painter = if(src !== null) rememberAsyncImagePainter(src)
        else painterResource(id = R.drawable.ic_avatar_white),
        modifier = modifier
            .size(80.dp)
            .clip(shape = RoundedCornerShape(40.dp))
            .background(MaterialTheme.colors.background)
            .layoutId("avatar")
            .border(
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.background),
                shape = RoundedCornerShape(40.dp)
            ),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop
    )
}