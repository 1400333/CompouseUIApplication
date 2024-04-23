package com.example.compouseuiapplication.basic

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compouseuiapplication.R
import com.example.compouseuiapplication.data.Message
import com.example.compouseuiapplication.ui.common.MessageCard.MessageCard3
import com.example.compouseuiapplication.ui.theme.CompouseUIApplicationTheme
import com.example.compouseuiapplication.ui.theme.CustomBtnBorder
import com.example.compouseuiapplication.ui.theme.CustomBtnDisabled
import com.example.compouseuiapplication.ui.theme.CustomBtnEnabled
import com.example.compouseuiapplication.ui.theme.CustomBtnPressed
import com.example.compouseuiapplication.ui.theme.CustomBtnTextDisabled
import com.example.compouseuiapplication.ui.theme.CustomBtnTextEnabled

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
                Surface(modifier = Modifier.fillMaxSize(),  //全螢幕填滿
                        color = MaterialTheme.colorScheme.background) {
                    Column {
                        MessageCard3(Message(author = getString(R.string.lesson3_sample_author),
                                             body = getString(R.string.lesson3_sample_body)))
                        CustomButton(onButtonClick = {},
                                     text = getString(R.string.lesson3_btn_text1))
                        CustomButton(onButtonClick = {},
                                     text = getString(R.string.lesson3_btn_text2),
                                     isEnabled = false)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomButton(
    onButtonClick: () -> Unit,
    text: String,
    isEnabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()    //Pressed、Dragged、Focused、Hovered

    Log.d("RDLog", "[isEnabled]${isEnabled}[isPressed]${isPressed}")

    var borderStroke: BorderStroke? = null
    val textColot: Color

    if (isEnabled) {
        borderStroke = BorderStroke(1.dp, CustomBtnBorder())
        textColot = CustomBtnTextEnabled()
    } else {
        textColot = CustomBtnTextDisabled()
    }

    val containerColor: Color

    if (isPressed) {
        containerColor = CustomBtnPressed();
    } else {
        containerColor = CustomBtnEnabled()
    }

    Button(onClick = onButtonClick,
           interactionSource = interactionSource,
           enabled = isEnabled,
           modifier = Modifier
               .fillMaxWidth()
               .padding(10.dp)
               .height(44.dp),
           shape = RoundedCornerShape(6.dp),
           colors = ButtonDefaults.buttonColors(containerColor = containerColor,
                                                disabledContainerColor = CustomBtnDisabled()),
           border = borderStroke) {
        Text(text = text,
             modifier = Modifier.align(Alignment.CenterVertically),
             color = textColot,
             fontSize = 16.sp)

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
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column {
                MessageCard3(Message("新聞快報",
                                     "有關於「00940之亂」，財訊傳媒董事長謝金河指出，從台灣的「錢多現象」來看，ETF規模還會往上升，政府該如何引導龎大游資，才是核心的問題。"))
                CustomButton(onButtonClick = {}, "測試標題")
                CustomButton(onButtonClick = {}, "測試標題", false)
            }
        }
    }
}

