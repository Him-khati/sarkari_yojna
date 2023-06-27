package com.himanshu.sarkari_yojna.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val baseTextStyle = TextStyle(
    fontFamily = Lato
)

val Typography = Typography(
    displayLarge = baseTextStyle.copy(
        fontSize = 57.sp,
        fontWeight = FontWeight.Normal
    ),
    displayMedium = baseTextStyle.copy(
        fontSize = 45.sp,
        fontWeight = FontWeight.Normal
    ),
    displaySmall = baseTextStyle.copy(
        fontSize = 36.sp,
        fontWeight = FontWeight.Normal
    ),

    headlineLarge = baseTextStyle.copy(
        fontSize = 32.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineMedium = baseTextStyle.copy(
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineSmall = baseTextStyle.copy(
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal
    ),


    titleLarge = baseTextStyle.copy(
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal
    ),
    titleMedium = baseTextStyle.copy(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    titleSmall = baseTextStyle.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),


    bodyLarge = baseTextStyle.copy(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = baseTextStyle.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = baseTextStyle.copy(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),

    labelLarge = baseTextStyle.copy(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    labelMedium = baseTextStyle.copy(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    ),
    labelSmall = baseTextStyle.copy(
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium
    )
)