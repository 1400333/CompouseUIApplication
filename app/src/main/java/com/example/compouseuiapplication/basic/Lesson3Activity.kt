package com.example.compouseuiapplication.basic

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compouseuiapplication.data.Message
import com.example.compouseuiapplication.ui.common.MessageCard.MessageCard3
import com.example.compouseuiapplication.ui.theme.CompouseUIApplicationTheme

/**
 * 第 3 課：深色主題
 * Surface 也是 @Composable
 * Modifier 更改元件的外觀，例：間距、高度、全螢幕填滿
 * CompouseUIApplicationTheme 設定深淺主題
 * @Preview 可同時預覽深淺主題
 */
class Lesson3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompouseUIApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),  //全螢幕填滿
                    color = MaterialTheme.colorScheme.background
                ) {
                    MessageCard3(Message("停機原因","對於「00940之亂」，財訊傳媒董事長謝金河指出，從台灣的「錢多現象」來看，ETF規模還會往上升，政府該如何引導龎大游資，才是核心的問題。"))
                }
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode",
)
@Composable
fun PreviewLesson3() {
    CompouseUIApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MessageCard3(Message("新聞快報","有關於「00940之亂」，財訊傳媒董事長謝金河指出，從台灣的「錢多現象」來看，ETF規模還會往上升，政府該如何引導龎大游資，才是核心的問題。"))
        }
    }
}