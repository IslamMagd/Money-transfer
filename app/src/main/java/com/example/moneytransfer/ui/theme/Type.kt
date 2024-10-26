package com.example.moneytransfer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.moneytransfer.R

val inter = FontFamily(
    Font(R.font.inter_18pt_regular),
    Font(R.font.inter_24pt_regular),
    Font(R.font.inter_28pt_regular),
    Font(R.font.inter_18pt_medium),
    Font(R.font.inter_24pt_medium),
    Font(R.font.inter_28pt_medium),
    Font(R.font.inter_24pt_semibold ,weight = FontWeight.SemiBold),
    Font(R.font.inter_28pt_semibold ,weight = FontWeight.SemiBold),

    )


val interGrayG0medium16 = TextStyle(
    fontFamily = inter,
    color = GrayG0,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp
)

val interRedP300medium16 = TextStyle(
    fontFamily = inter,
    color = RedP300,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp
)

val boldBlackCenter28 = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 28.sp,
    textAlign = TextAlign.Center,
    color = Color.Black
)

val mediumBlackCenter24 = TextStyle(
    fontWeight = FontWeight(500),
    fontSize = 24.sp,
    textAlign = TextAlign.Center,
    color = Color.Black
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)