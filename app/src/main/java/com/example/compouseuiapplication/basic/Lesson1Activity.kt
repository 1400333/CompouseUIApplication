package com.example.compouseuiapplication.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compouseuiapplication.R
import com.example.compouseuiapplication.ui.common.MessageCard
import com.example.compouseuiapplication.ui.theme.CustomMessageCard1Theme

/**
 * 第 1 課：可組合函式
 * 認識 @Composable、@Preview
 * 認識 ComponentActivity :使用compose UI要繼承的Activity
 * 認識 CustomMessageCard1Theme 自定義樣式
 * Text 文字
 */
class Lesson1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomMessageCard1Theme {
                MessageCard.MessageCard1(getString(R.string.lesson1_sample_text))
            }

        }
    }
}

@Preview
@Composable
fun PreviewLesson1() {
    CustomMessageCard1Theme {
        MessageCard.MessageCard1("預覽文字")
    }
}