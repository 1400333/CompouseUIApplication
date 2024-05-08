package com.example.compouseuiapplication.advanced

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.compouseuiapplication.data.CountryData
import com.example.compouseuiapplication.util.LogUtil

/**
 * 進階：Compose中的ViewModel
 * api串接、viewModels collectAsState
 */
class RequestSampleActivity : ComponentActivity() {
    private val m_viewModel: RequestSampleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            onLoading(m_viewModel)
            initCountry(m_viewModel)
        }
    }
}

@Composable
fun initCountry(viewModel: RequestSampleViewModel) {
    val countryDataList by viewModel.countryDataList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.querySampleData()
        LogUtil.log("[LaunchedEffect]")
    }

    LazyColumn {
        items(countryDataList) { countryData ->
            CountryCard(countryData)
        }
    }
    LogUtil.log("[initCountry]${countryDataList.size}")
}

@Composable
fun CountryCard(countryData: CountryData) {
    Text(text = "[CountryId] ${countryData.getCountryId()}!")
}

@Composable
fun onLoading(viewModel: RequestSampleViewModel) {
    val isLoading by viewModel.bLoading.collectAsState()

    showProgress(isLoading)
}

@Composable
fun showProgress(bShow: Boolean) {
    if (bShow) {
        Dialog(onDismissRequest = { },
               DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(White, shape = RoundedCornerShape(8.dp))) {
                CircularProgressIndicator()
            }
        }
    }

}