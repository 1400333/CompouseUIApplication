package com.example.compouseuiapplication.basic

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
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
import com.example.compouseuiapplication.ui.theme.CompouseUIApplicationTheme
import com.example.compouseuiapplication.ui.theme.CustomBtnBorder
import com.example.compouseuiapplication.ui.theme.CustomBtnDisabled
import com.example.compouseuiapplication.ui.theme.CustomBtnEnabled
import com.example.compouseuiapplication.ui.theme.CustomBtnPressed
import com.example.compouseuiapplication.ui.theme.CustomBtnTextDisabled
import com.example.compouseuiapplication.ui.theme.CustomBtnTextEnabled

/**
 * 第 6 課：客製化按鈕
 */
class Lesson6Activity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompouseUIApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column {
                        CustomButton(onButtonClick = {}, getString(R.string.lesson6_btn_text))
                        CustomButton(onButtonClick = {}, getString(R.string.lesson6_btn_text), false)
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
    val isPressed by interactionSource.collectIsPressedAsState()    //是否點擊中
    // Pressed : collectIsPressedAsState()
    // Dragged : collectIsDraggedAsState()
    // Focused : collectIsFocusedAsState()
    // Hovered : collectIsHoveredAsState()

    var borderStroke: BorderStroke? = null
    val textColor: Color

    if (isEnabled) {
        borderStroke = BorderStroke(1.dp, CustomBtnBorder())    //enabled時邊框顏色
        textColor = CustomBtnTextEnabled()                      //enabled時文字顏色
    } else {                                                    //disabled時無邊框
        textColor = CustomBtnTextDisabled()                     //disabled時文字顏色
    }

    val containerColor: Color

    if (isPressed) {
        containerColor = CustomBtnPressed() //點擊時的顏色
    } else {
        containerColor = CustomBtnEnabled() //放開時的顏色
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
                                                disabledContainerColor = CustomBtnDisabled()), //disabled時的顏色
           border = borderStroke) {
        Text(text = text,
             modifier = Modifier.align(Alignment.CenterVertically),
             color = textColor,
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
fun PreviewLesson6() {
    CompouseUIApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column {
                CustomButton(onButtonClick = {}, "取消")
                CustomButton(onButtonClick = {}, "取消", false)
            }
        }
    }
}
