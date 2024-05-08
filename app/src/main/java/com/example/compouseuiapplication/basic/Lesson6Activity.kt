package com.example.compouseuiapplication.basic

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.compouseuiapplication.ui.common.MessageCard3
import com.example.compouseuiapplication.ui.theme.CompouseUIApplicationTheme
import com.example.compouseuiapplication.ui.theme.CustomBtnBorder
import com.example.compouseuiapplication.ui.theme.CustomBtnDisabled
import com.example.compouseuiapplication.ui.theme.CustomBtnEnabled
import com.example.compouseuiapplication.ui.theme.CustomBtnPressed
import com.example.compouseuiapplication.ui.theme.CustomBtnTextDisabled
import com.example.compouseuiapplication.ui.theme.CustomBtnTextEnabled
import com.example.compouseuiapplication.util.LogUtil

class Lesson6Activity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompouseUIApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column {
                        CustomButton(onButtonClick = {}, "測試標題")
                        CustomButton(onButtonClick = {}, "測試標題", false)
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

    LogUtil.log("[isEnabled]${isEnabled}[isPressed]${isPressed}")

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
fun PreviewLesson6() {
    CompouseUIApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column {
                CustomButton(onButtonClick = {}, "測試標題")
                CustomButton(onButtonClick = {}, "測試標題", false)
            }
        }
    }
}
