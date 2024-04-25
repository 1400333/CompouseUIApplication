package com.example.compouseuiapplication.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

//深色主題
private val DarkColorScheme = darkColorScheme(primary = plight, secondary = plight40, tertiary = black272727)

//淺色主題
private val LightColorScheme = lightColorScheme(primary = sky_blue, secondary = nice_blue, tertiary = white)


@Composable
fun CompouseUIApplicationTheme(darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
                               dynamicColor: Boolean = true, content: @Composable () -> Unit) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            //用primary作為 statusBarColor
            window.statusBarColor = colorScheme.primary.toArgb()
            //深色系狀態列icon用深色
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(colorScheme = colorScheme,  //顏色主題
                  typography = Typography,    //字體
                  content = content           //聲明視圖
    )
}

/**
 * MessageCard1自定義樣式
 * @param darkTheme 深色模式，預設採系統設定
 * @param content 套用此樣式的 @Composable
 */
@Composable
fun CustomMessageCard1Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    //文字顏色
    val textColor: Color = when {
        darkTheme -> white  //深色模式
        else -> nice_blue   //亮色模式
    }
    //文字背景顏色
    val textBackgroundColor: Color = when {
        darkTheme -> black272727    //深色模式
        else -> white               //亮色模式
    }

    //建立字體
    val typography = Typography(bodyLarge = TextStyle(fontFamily = FontFamily.Default,
                                                      fontWeight = FontWeight.Normal,
                                                      fontSize = 40.sp,
                                                      color = textColor,
                                                      background = textBackgroundColor,
                                                      lineHeight = 24.sp,
                                                      letterSpacing = 0.5.sp))

    //為@Composable 套上樣式
    MaterialTheme(typography = typography,    //字體
                  content = content           //聲明視圖，即@Composable
    )
}
