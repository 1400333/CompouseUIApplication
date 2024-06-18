package com.example.compouseuiapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.compouseuiapplication.advanced.EffectActivity
import com.example.compouseuiapplication.advanced.RequestSampleActivity
import com.example.compouseuiapplication.basic.Lesson1Activity
import com.example.compouseuiapplication.basic.Lesson2Activity
import com.example.compouseuiapplication.basic.Lesson3Activity
import com.example.compouseuiapplication.basic.Lesson4Activity
import com.example.compouseuiapplication.basic.Lesson5Activity
import com.example.compouseuiapplication.basic.Lesson6Activity
import com.example.compouseuiapplication.data.LessonInfo
import com.example.compouseuiapplication.data.SampleData
import com.example.compouseuiapplication.ui.theme.AppBackgroundColor
import com.example.compouseuiapplication.ui.theme.CompouseUIApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompouseUIApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(),  //全螢幕填滿
                        color = AppBackgroundColor()) {
                    setMainLessons(SampleData.lessonlist)
                }

            }
        }
    }
}

@Composable
fun LessonBtn(lessonInfo: LessonInfo) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { /* Handle the result if needed */ }

    Button(onClick = {
        val intent = onClickLesson(context, lessonInfo)
        // Start the activity using the launcher
        launcher.launch(intent)
    }, modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .height(50.dp)) {
        Text(text = lessonInfo.strLessonTitle,
             modifier = Modifier.align(Alignment.CenterVertically))

    }
}

fun onClickLesson(context: Context, info: LessonInfo): Intent? {
    var intent: Intent? = null

    when (info.iLessonId) {

        LessonInfo.LESSON_1_COMPOSABLE -> {
            intent = Intent(context, Lesson1Activity::class.java)
        }

        LessonInfo.LESSON_2_INLINE -> {
            intent = Intent(context, Lesson2Activity::class.java)
        }

        LessonInfo.LESSON_3_THEME -> {
            intent = Intent(context, Lesson3Activity::class.java)
        }

        LessonInfo.LESSON_4_LAZY_COLUMN -> {
            intent = Intent(context, Lesson4Activity::class.java)
        }

        LessonInfo.LESSON_5_REMEMBER_MUTABLE -> {
            intent = Intent(context, Lesson5Activity::class.java)
        }

        LessonInfo.LESSON_6_CUSTOME_BTN -> {
            intent = Intent(context, Lesson6Activity::class.java)
        }

        LessonInfo.LESSON_ADV_REQ_VIEWMODEL -> {
            intent = Intent(context, RequestSampleActivity::class.java)
        }

        LessonInfo.LESSON_ADV_EFFECT ->{
            intent = Intent(context, EffectActivity::class.java)
        }
    }

    return intent
}

@Composable
fun setMainLessons(lessonInfoList: List<LessonInfo>) {
    LazyColumn {
        items(lessonInfoList) { lessonInfo ->
            LessonBtn(lessonInfo)
        }
    }
}

@Preview
@Composable
fun PreviewMain() {
    CompouseUIApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize(),  //全螢幕填滿
                color = AppBackgroundColor()) {
            setMainLessons(SampleData.lessonlist)
        }

    }
}