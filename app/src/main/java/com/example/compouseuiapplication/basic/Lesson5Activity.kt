package com.example.compouseuiapplication.basic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compouseuiapplication.util.LogUtil


/**
 * 第 5 課：remember和mutableStateOf
 */
class Lesson5Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterLesson5_4_remember()
            //TestSideEffect()
        }
    }
}

/**
 * 範例:數十秒（確保每次 TestSideEffect 組合完成都會執行 SideEffect ）
 */
@Composable
fun TestSideEffect() {
    Surface {
        var iCount by remember { mutableStateOf(0) }

        SideEffect {
            if (iCount < 10) {
                iCount++
            }
            Thread.sleep(1000)
            LogUtil.log("----[iCount] = $iCount ")
        }

        Text(text = "I have been clicked ${iCount} times")

    }
}

/**
 * 5_1 未使用mutableStateOf
 * 點擊按鈕，數字不變，只有印一次
 */
@Composable
fun CounterLesson5_1() {
    Surface() {
        var iCount: Int = 0

        Button(onClick = { iCount++ },
               modifier = Modifier
                   .padding(16.dp)
                   .fillMaxWidth()
                   .height(50.dp)) {
            Text(text = "I have been clicked ${iCount} times",
                 modifier = Modifier.align(Alignment.CenterVertically))


            SideEffect(effect = { LogUtil.log("---- text count = $iCount ") })

        }
        SideEffect(effect = { LogUtil.log("---- out count = $iCount ") })

    }
}

/**
 * 5_2 用mutableStateOf
 * 講解：
 * 1.var iCount by mutableStateOf(0)在Surface底下，而Surface底下的Composable沒有用到iCount，所以只會宣告一次。
 * 2.Button底下Composable(Text)「有用到iCount」所以當iCount有變化時Button的Text會重繪，Button不會重繪。
 * 疑惑：此處發生錯誤表示沒有使用remember
 */
@Composable
fun CounterLesson5_2() {
    Surface() {
        var iCount by mutableStateOf(0)

        Button(onClick = { iCount++ },
               modifier = Modifier
                   .padding(16.dp)
                   .fillMaxWidth()
                   .height(50.dp)) {

            Text(text = "I have been clicked ${iCount} times",
                 modifier = Modifier.align(Alignment.CenterVertically))


            SideEffect(effect = { LogUtil.log("---- text count = $iCount ") })

        }
        SideEffect(effect = { LogUtil.log("---- out count = $iCount ") })

    }
}

/**
 * 5_3 把Button的padding改成跟count有關
 * 講解：
 * 1.var iCount by mutableStateOf(0)在Surface底下。
 * 2.Surface底下Composable(Button)「有用到iCount」所以當iCount有變化時，Surface內部組件會重繪。
 * 3.當 Surface內部組件重繪時，又走過 var iCount by mutableStateOf(0) 數值再次重置為0。
 * 疑惑：此處發生錯誤表示沒有使用remember
 */
@Composable
fun CounterLesson5_3() {
    Surface() {
        var iCount by mutableStateOf(0)

        Button(onClick = { iCount++ },
               modifier = Modifier
                   .padding(iCount.dp)  //把Button的padding改成跟count有關
                   .fillMaxWidth()
                   .height(50.dp)) {
            Text(text = "I have been clicked ${iCount} times",
                 modifier = Modifier.align(Alignment.CenterVertically))


            SideEffect(effect = { LogUtil.log("---- text count = $iCount ") })

        }
        SideEffect(effect = { LogUtil.log("---- out count = $iCount ") })

    }
}

/**
 * 5_4 使用remember + mutableStateOf
 * 講解：
 * 1.var iCount by mutableStateOf(0)在Surface底下。
 * 2.Surface底下Composable(Button)「有用到iCount」所以當iCount有變化時，Surface內部組件會重繪，因remember，記憶體中存有iCount變化後的數值。
 * 3.當 Surface內部組件重繪時，又走過 var iCount by remember{ mutableStateOf(0)} 會從內存中讀取。
 */
@Preview
@Composable
fun CounterLesson5_4_remember() {
    Surface() {
        var iCount by remember { mutableStateOf(0) }   //使用remember

        Button(onClick = { iCount++ },
               modifier = Modifier
                   .padding(iCount.dp)  //把Button的padding改成跟count有關
                   .fillMaxWidth()
                   .height(50.dp)) {
            Text(text = "I have been clicked ${iCount} times",
                 modifier = Modifier.align(Alignment.CenterVertically))


            SideEffect(effect = { LogUtil.log("---- text count = $iCount ") })

        }
        SideEffect(effect = { LogUtil.log("---- out count = $iCount ") })

    }
}

/**
 * 5_5 outside Surface
 * 講解：
 * 1.var iCount by mutableStateOf(0) 在Surface 外面，setContent底下。
 * 2.Surface底下Composable(Button)「有用到iCount」所以當iCount有變化時，Surface內部組件會重繪。
 * 3.當Surface內部組件重繪時，不會走過 var iCount by mutableStateOf(0)，所以值不會被重置，可以取到增加的值。
 * 疑惑：此處發生錯誤表示沒有使用remember
 */
@Composable
fun CounterLesson5_5() {
    var iCount by mutableStateOf(0)
    Surface() {
        Button(onClick = { iCount++ },
               modifier = Modifier
                   .padding(iCount.dp)  //把Button的padding改成跟count有關
                   .fillMaxWidth()
                   .height(50.dp)) {
            Text(text = "I have been clicked ${iCount} times",
                 modifier = Modifier.align(Alignment.CenterVertically))


            SideEffect(effect = { LogUtil.log("---- text count = $iCount ") })

        }
        SideEffect(effect = { LogUtil.log("---- out count = $iCount ") })

    }
}

/**
 * 5_6 outside inline
 * 講解：
 * 1.var iCount by mutableStateOf(0) 在Column 外面，setContent底下。
 * 2.Column屬於inline函数，內部重繪時，他們也會跟著重繪，也就是setContent底下的Column要重繪了(setContent內部組件重繪)。
 * 3.setContent內部組件重繪，又走過var iCount by mutableStateOf(0) 數值再次重置為0。
 */
@Composable
fun CounterLesson5_6() {
    var iCount by mutableStateOf(0)
    Column() {
        Button(onClick = { iCount++ },
               modifier = Modifier
                   .padding(iCount.dp)  //把Button的padding改成跟count有關
                   .fillMaxWidth()
                   .height(50.dp)) {
            Text(text = "I have been clicked ${iCount} times",
                 modifier = Modifier.align(Alignment.CenterVertically))

            SideEffect(effect = { LogUtil.log("---- text count = $iCount ") })

        }
        SideEffect(effect = { LogUtil.log("---- out count = $iCount ") })

    }
}