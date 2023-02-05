package org.includejoe.markety.feature_user.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import org.includejoe.markety.R

@Composable
fun CoverImage(src: String? = null) {
    Image(
        painter = if(src !== null) rememberAsyncImagePainter(src)
        else painterResource(id = R.drawable.cover_photo_placeholder),
        modifier = Modifier
            .fillMaxSize()
            .layoutId("coverImage"),
        contentDescription = "coverImage",
        contentScale = ContentScale.Crop
    )
}