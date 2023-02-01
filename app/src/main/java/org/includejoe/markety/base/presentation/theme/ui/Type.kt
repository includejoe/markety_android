package org.includejoe.markety.base.presentation.theme.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.includejoe.markety.R

val poppins = FontFamily(
    listOf(
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_black, FontWeight.Black),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        color = TextWhite,
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    h1 = TextStyle(
        color = TextWhite,
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    h2 = TextStyle(
        color = TextWhite,
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
)