package com.example.compouseuiapplication.advanced

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compouseuiapplication.util.LogUtil

class EffectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EffectClickedTest4()
        }
    }
}

@Composable
fun EffectClickedTest1() {
    Surface() {
        val clicked = remember { mutableStateOf(0) }

        LogUtil.log("disposable effect before: ${clicked.value}")

        DisposableEffect(key1 = clicked.value) {
            LogUtil.log("disposable effect called")

            onDispose {
                LogUtil.log("disposable effect disposed")
            }
        }
        LogUtil.log("disposable effect after: ${clicked.value}")

        Column {
            Text(text = "Clicked: ${clicked.value}", modifier = Modifier
                .padding(16.dp)
                .clickable {
                    clicked.value = clicked.value + 1
                })
        }
    }
}

@Composable
fun EffectClickedTest2() {
    Surface() {
        val clicked = remember { mutableStateOf(0) }

        LogUtil.log("disposable effect before: ${clicked.value}")

        DisposableEffect(key1 = clicked.value) {
            LogUtil.log("disposable effect called")

            onDispose {
                LogUtil.log("disposable effect disposed")
            }
        }
        LogUtil.log("disposable effect after: ${clicked.value}")

        DisposableEffect(key1 = clicked.value) {
            LogUtil.log("disposable effect called [2]")

            onDispose {
                LogUtil.log("disposable effect disposed [2]")
            }
        }

        Column {
            Text(text = "Clicked: ${clicked.value}", modifier = Modifier
                .padding(16.dp)
                .clickable {
                    clicked.value = clicked.value + 1
                })
        }
    }
}

@Composable
fun EffectClickedTest3() {
    Surface() {
        val clicked = remember { mutableStateOf(0) }

        LogUtil.log("disposable effect before: ${clicked.value}")

        DisposableEffect(key1 = clicked.value) {
            LogUtil.log("disposable effect called")

            onDispose {
                LogUtil.log("disposable effect disposed")
            }
        }
        SideEffect {
            LogUtil.log("side effect called [1]")
        }

        LogUtil.log("disposable effect after: ${clicked.value}")

        DisposableEffect(key1 = clicked.value) {
            LogUtil.log("disposable effect called [2]")

            onDispose {
                LogUtil.log("disposable effect disposed [2]")
            }
        }

        SideEffect {
            LogUtil.log("side effect called [2]")
        }

        Column {
            Text(text = "Clicked: ${clicked.value}", modifier = Modifier
                .padding(16.dp)
                .clickable {
                    clicked.value = clicked.value + 1
                })
        }
    }
}

@Composable
fun EffectClickedTest4() {
    Surface() {
        val clicked = remember { mutableStateOf(0) }

        LogUtil.log("disposable effect before: ${clicked.value}")

        DisposableEffect(key1 = clicked.value) {
            LogUtil.log("disposable effect called")

            onDispose {
                LogUtil.log("disposable effect disposed")
            }
        }
        SideEffect {
            LogUtil.log("side effect called [1]")
        }

        LaunchedEffect(key1 = clicked.value) {
            LogUtil.log("launched effect called [1]")
        }

        LogUtil.log("disposable effect after: ${clicked.value}")

        DisposableEffect(key1 = clicked.value) {
            LogUtil.log("disposable effect called [2]")

            onDispose {
                LogUtil.log("disposable effect disposed [2]")
            }
        }

        SideEffect {
            LogUtil.log("side effect called [2]")
        }

        LaunchedEffect(key1 = clicked.value) {
            LogUtil.log("launched effect called [2]")
        }

        Column {
            Text(text = "Clicked: ${clicked.value}", modifier = Modifier
                .padding(16.dp)
                .clickable {
                    clicked.value = clicked.value + 1
                })
        }
    }
}