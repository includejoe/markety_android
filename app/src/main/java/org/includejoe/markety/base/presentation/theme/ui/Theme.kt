package org.includejoe.markety.base.presentation.theme.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Blue,
    primaryVariant = DarkBlue500,
    secondary = Pink,
    background = DarkBlue700,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    surface = Color(0xFF1D2038),
    onSurface = LightGray,
    error = Red500,
)

private val LightColorPalette = lightColors(
    primary = Blue,
    primaryVariant = White,
    secondary = Pink,
    background = White,
    onPrimary = White,
    onSecondary = White,
    onBackground = Black,
    surface = Color(0xFFD9D9d9),
    onSurface = DarkGray,
    error = Red700
)

@Composable
fun MarketyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if(darkTheme) DarkBlue500 else White
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}