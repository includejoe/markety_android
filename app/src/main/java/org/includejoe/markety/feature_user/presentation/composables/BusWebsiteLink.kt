package org.includejoe.markety.feature_user.presentation.composables

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun BusWebsiteLink(
    link: String
){
    val uriHandler = LocalUriHandler.current
    val annotatedString = buildAnnotatedString {
        append(link)

        val start = 0
        val end = link.length

        addStyle(
            SpanStyle(
                color = MaterialTheme.colors.secondary,
                textDecoration = TextDecoration.Underline,
                fontSize = MaterialTheme.typography.body1.fontSize,
                fontStyle = FontStyle.Italic,
            ),
            start = start,
            end = end
        )

        addStringAnnotation(
            tag ="busWebsite",
            annotation = link,
            start = start,
            end = end
        )
    }

    ClickableText(
        text = annotatedString,
        style = MaterialTheme.typography.body1
    ) {
        uriHandler.openUri(link)
    }
}