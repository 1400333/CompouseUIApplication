package com.example.compouseuiapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.Color

//色票
val black272727 = Color(0xFF272727)
val black = Color(0xFF000000)
val greyish_brown = Color(0xFF484848)
val line_gray = Color(0xFFe0e0e0)
val text_gray = Color(0xFF989898)
val sky_blue = Color(0xFF66adff)
val dark_sky_blue = Color(0xFF3a8ce9)
val nice_blue = Color(0xFF0b57a6)
val plight = Color(0xFFc7e1ff)
val plight40 = Color(0xFFe9f3ff)
val white = Color(0xFFFFFFFF)

//自定義顏色
@Composable
fun AppBackgroundColor(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    val colorRet: Color = when {
        darkTheme -> nice_blue
        else -> white
    }

    return colorRet
}

@Composable
fun CustomBtnEnabled(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    val colorRet: Color = when {
        darkTheme -> greyish_brown
        else -> white
    }

    return colorRet
}

@Composable
fun CustomBtnDisabled(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    val colorRet: Color = when {
        darkTheme -> greyish_brown
        else -> line_gray
    }

    return colorRet
}

@Composable
fun CustomBtnBorder(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    val colorRet: Color = when {
        darkTheme -> sky_blue
        else -> dark_sky_blue
    }

    return colorRet
}

@Composable
fun CustomBtnTextEnabled(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    val colorRet: Color = when {
        darkTheme -> sky_blue
        else -> dark_sky_blue
    }

    return colorRet
}

@Composable
fun CustomBtnTextDisabled(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    val colorRet: Color = when {
        darkTheme -> text_gray
        else -> text_gray
    }

    return colorRet
}