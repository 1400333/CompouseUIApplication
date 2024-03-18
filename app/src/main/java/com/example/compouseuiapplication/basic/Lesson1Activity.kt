package com.example.compouseuiapplication.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compouseuiapplication.ui.common.MessageCard

/**
 * 第 1 課：可組合函式
 * 認識 @Composable、@Preview
 * 認識 ComponentActivity :使用compose UI要繼承的Activity
 * Text 文字
 */
class Lesson1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard.MessageCard1("精誠隨想")
        }
    }
}

@Preview
@Composable
fun PreviewLesson1() {
    MessageCard.MessageCard1("預覽文字")
}