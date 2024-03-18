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
                    MessageCard3(Message("停機原因","為了提供更優質的服務，TIPTOP相關系統將進行系統維護更新。維護期間，為確保資料一致性，將全面停止 EIP & TIPTOP相關系統的系統服務。"))
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
            MessageCard3(Message("停機原因","為了提供更優質的服務，TIPTOP相關系統將進行系統維護更新。維護期間，為確保資料一致性，將全面停止 EIP & TIPTOP相關系統的系統服務。"))
        }
    }
}