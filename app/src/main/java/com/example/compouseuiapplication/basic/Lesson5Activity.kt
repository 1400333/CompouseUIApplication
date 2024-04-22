package com.example.compouseuiapplication.basic

import android.annotation.SuppressLint
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


/**
 * 第 5 課：remember和mutableStateOf
 */
class Lesson5Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d("RDLog", "---- clicked onCreated setContent ")

            PreviewLesson5_6();
        }
    }
}

/**
 * 5_1
 * 點擊按鈕，數字不變，只有印一次
 */
@Composable
fun PreviewLesson5_1() {
    Surface() {
        var iCount: Int = 0

        Button(onClick = { iCount++ },
               modifier = Modifier
                   .padding(16.dp)
                   .fillMaxWidth()
                   .height(50.dp)) {
            Text(text = "I have been clicked ${iCount} times",
                 modifier = Modifier.align(Alignment.CenterVertically))


            SideEffect(effect = { Log.d("RDLog", "---- text count = $iCount ") })

        }
        SideEffect(effect = { Log.d("RDLog", "---- out count = $iCount ") })

    }
}

/**
 * 5_2
 * 講解：
 * 1.var iCount by mutableStateOf(0)在Surface底下，而Surface底下的Composable沒有用到iCount，所以只會宣告一次
 * 2.Button底下Composable(Text)「有用到iCount」所以當iCount有變化時Button的Text會重繪，Button不會重繪
 * 疑惑：此處發生錯誤表示沒有使用remember
 */
@Composable
fun PreviewLesson5_2() {
    Surface() {
        var iCount by mutableStateOf(0)

        Button(onClick = { iCount++ },
               modifier = Modifier
                   .padding(16.dp)
                   .fillMaxWidth()
                   .height(50.dp)) {
       
            Text(text = "I have been clicked ${iCount} times",
                 modifier = Modifier.align(Alignment.CenterVertically))


            SideEffect(effect = { Log.d("RDLog", "---- text count = $iCount ") })

        }
        SideEffect(effect = { Log.d("RDLog", "---- out count = $iCount ") })

    }
}

/**
 * 5_3 把Button的padding改成跟count有關
 * 講解：
 * 1.var iCount by mutableStateOf(0)在Surface底下，
 * 2.Surface底下Composable(Button)「有用到iCount」所以當iCount有變化時Surface會重繪
 * 3.當 Surface重繪時又走過 var iCount by mutableStateOf(0) 又被重置為0
 * 疑惑：此處發生錯誤表示沒有使用remember
 */
@Composable
fun PreviewLesson5_3() {
    Surface() {
        var iCount by mutableStateOf(0)

        Button(onClick = { iCount++ },
               modifier = Modifier
                   .padding(iCount.dp)  //把Button的padding改成跟count有關
                   .fillMaxWidth()
                   .height(50.dp)) {
            Text(text = "I have been clicked ${iCount} times",
                 modifier = Modifier.align(Alignment.CenterVertically))


            SideEffect(effect = { Log.d("RDLog", "---- text count = $iCount ") })

        }
        SideEffect(effect = { Log.d("RDLog", "---- out count = $iCount ") })

    }
}

/**
 * 5_4 remember
 * 講解：
 * 1.var iCount by remember{ mutableStateOf(0)}在Surface底下，
 * 2.Surface底下Composable(Button)「有用到iCount」所以當iCount有變化時Surface會重繪
 * 3.當 Surface重繪時又走過 var iCount by remember{ mutableStateOf(0)} 每次值會從內存中讀取
 * 疑惑：此處發生錯誤表示沒有使用remember
 */
@Composable
fun PreviewLesson5_4_remember() {
    Surface() {
        var iCount by remember{ mutableStateOf(0)}   //使用remember

        Button(onClick = { iCount++ },
               modifier = Modifier
                   .padding(iCount.dp)  //把Button的padding改成跟count有關
                   .fillMaxWidth()
                   .height(50.dp)) {
            Text(text = "I have been clicked ${iCount} times",
                 modifier = Modifier.align(Alignment.CenterVertically))


            SideEffect(effect = { Log.d("RDLog", "---- text count = $iCount ") })

        }
        SideEffect(effect = { Log.d("RDLog", "---- out count = $iCount ") })

    }
}
/**
 * 5_5 outside Surface
 * 講解：
 * 1.var iCount by mutableStateOf(0) 在setContent底下，但底下的Surface沒用到iCount不會重繪
 * 2.Surface底下Composable(Button)「有用到iCount」所以當iCount有變化時Surface會重繪
 * 3.當 Surface重繪時不會走過 var iCount by mutableStateOf(0)，所以值不會被重置可以取到增加的值
 * 疑惑：此處發生錯誤表示沒有使用remember
 */

@Composable
fun PreviewLesson5_5() {
    var iCount by mutableStateOf(0)
    Surface() {
        Button(onClick = { iCount++ },
               modifier = Modifier
                   .padding(iCount.dp)  //把Button的padding改成跟count有關
                   .fillMaxWidth()
                   .height(50.dp)) {
            Text(text = "I have been clicked ${iCount} times",
                 modifier = Modifier.align(Alignment.CenterVertically))


            SideEffect(effect = { Log.d("RDLog", "---- text count = $iCount ") })

        }
        SideEffect(effect = { Log.d("RDLog", "---- out count = $iCount ") })

    }
}

/**
 * 5_6 outside inline
 * 講解：
 * 1.var iCount by mutableStateOf(0) 在setContent底下
 * 1.Column屬於inline 函数內部重匯時，他們也會跟著重繪，也就是setContent底下有人要重繪了，
 * 就會經過 var iCount by mutableStateOf(0) 重置為0
 */
@Composable
fun PreviewLesson5_6() {
    var iCount by mutableStateOf(0)
    Column() {
        Button(onClick = { iCount++ },
               modifier = Modifier
                   .padding(iCount.dp)  //把Button的padding改成跟count有關
                   .fillMaxWidth()
                   .height(50.dp)) {
            Text(text = "I have been clicked ${iCount} times",
                 modifier = Modifier.align(Alignment.CenterVertically))


            SideEffect(effect = { Log.d("RDLog", "---- text count = $iCount ") })

        }
        SideEffect(effect = { Log.d("RDLog", "---- out count = $iCount ") })

    }
}