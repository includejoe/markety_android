package org.includejoe.markety.base.presentation.theme.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

private val DarkColorPalette = darkColors(
    primary = Primary,
    secondary = Secondary,
    background = DarkBg700,
    onPrimary = TextWhite,
    onSecondary = TextWhite,
    onBackground = TextWhite,
    surface = Color.White,
    onSurface = TextDark,
    error = Error500
)

private val LightColorPalette = lightColors(
    primary = Primary,
    secondary = Secondary,
    background = LightBg,
    onPrimary = TextWhite,
    onSecondary = TextWhite,
    onBackground = TextDark,
    surface = DarkBg500,
    onSurface = TextWhite,
    error = Error700
)

@Composable
fun MarketyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}