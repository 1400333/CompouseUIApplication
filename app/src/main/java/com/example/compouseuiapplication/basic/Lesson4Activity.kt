package com.example.compouseuiapplication.basic

import android.content.res.Configuration
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compouseuiapplication.data.Message
import com.example.compouseuiapplication.data.SampleData
import com.example.compouseuiapplication.ui.common.MessageCard.MessageCard4
import com.example.compouseuiapplication.ui.theme.CompouseUIApplicationTheme
import com.example.compouseuiapplication.ui.theme.AppBackgroundColor

/**
 * 第 4 課：列表展示
 * AppBackgroundColor 自定義顏色
 * LazyColumn 列表顯示資料
 */
class Lesson4Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompouseUIApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(),  //全螢幕填滿
                        color = AppBackgroundColor()) {
                    Conversation(SampleData.conversationSample)
                }

            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard4(message)
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
fun PreviewConversation() {
    CompouseUIApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize(),  //全螢幕填滿
                color = AppBackgroundColor()) {
            Conversation(SampleData.conversationSample)
        }


    }
}
