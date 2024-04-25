package com.example.compouseuiapplication.advanced

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.compouseuiapplication.data.CountryData


class RequestSampleActivity : ComponentActivity() {
    private val m_viewModel: RequestSampleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Conversation(m_viewModel)

            m_viewModel.querySampleData()
        }
    }
}

@Composable
fun Conversation(viewModel: RequestSampleViewModel) {
    val countryDataList by viewModel.countryDataList.collectAsState()

    LazyColumn {
        items(countryDataList) { countryData ->
            CountryCard(countryData)
        }
    }
}

@Composable
fun CountryCard(countryData: CountryData) {
    Text(text = "[CountryId] ${countryData.getCountryId()}!")
}