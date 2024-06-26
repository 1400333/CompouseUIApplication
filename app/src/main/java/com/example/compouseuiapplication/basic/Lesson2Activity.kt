package com.example.compouseuiapplication.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compouseuiapplication.R
import com.example.compouseuiapplication.data.Message
import com.example.compouseuiapplication.ui.common.MessageCard2

/**
 * 第 2 課：版面配置
 * Column、Row、Box 排版
 * Image 圖片
 * Modifier 更改元件的外觀，例：間距、高度
 * Spacer 空白的布局
 */
class Lesson2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard2(Message(author = getString(R.string.lesson2_sample_author),
                                             body = getString(R.string.lesson2_sample_body)))
        }
    }
}

@Preview
@Composable
fun PreviewLesson2() {
    MessageCard2(Message(author = "Android Studio",
                                     body = "Android Studio 帶來許多專屬於 Jetpack Compose 的新功能，它支援採用程式碼優先方法，同時還能提高開發人員的工作效率，而不需要在設計介面或程式碼編輯器中二選一。"))
}