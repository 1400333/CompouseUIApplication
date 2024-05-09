package com.example.compouseuiapplication.advanced

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import com.example.compouseuiapplication.api.OnDataRecovery
import com.example.compouseuiapplication.api.RequestData
import com.example.compouseuiapplication.api.RequestManager
import com.example.compouseuiapplication.api.ResponseData
import com.example.compouseuiapplication.data.CountryData
import com.example.compouseuiapplication.data.CountryDataList
import com.example.compouseuiapplication.util.UrlUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RequestSampleViewModel : AndroidViewModel {
    private val m_reqManager: RequestManager = RequestManager()

    private val m_bLoading = MutableStateFlow(false) // 是否要顯示轉圈圈
    val bLoading: StateFlow<Boolean> get() = m_bLoading

    private var m_iBlockingCount = 0 //強制轉圈數
    private var m_iNonBlockingCount = 0 //未強制轉圈數

    private val m_countryDataList = MutableStateFlow<List<CountryData>>(mutableListOf())
    val countryDataList: StateFlow<List<CountryData>> get() = m_countryDataList

    private val m_handler = Handler(Looper.getMainLooper())

    private val _iCount = MutableStateFlow<Int>(0)
    val count: StateFlow<Int> get() = _iCount

    constructor(application: Application) : super(application) {

    }

    fun addCount() {
        m_handler.postDelayed(Runnable {
            if (_iCount.value < 10) {
                _iCount.value += 1
            }
        }, 1000)
    }

    fun querySampleData() {
        postData(UrlUtil.newGetRequestObj(getApplication(),
                                          UrlUtil.WS_CMD_GET_SAMPLE_DATA,
                                          UrlUtil.packReqQueryGetSample("nathaniel"),
                                          true)) { responseData: ResponseData? ->

            m_countryDataList.value =
                CountryDataList.parseCountryList(responseData?.m_joRespData).getCountryList()
        }
    }

    private fun postData(requestData: RequestData, onDataRecovery: OnDataRecovery) {
        if (requestData.isBlocking()) {
            addBlockingCount()
            setLoading(true)
        } else {
            addNonBlockingCount()
        }

        m_reqManager.postData(requestData) { responseData: ResponseData? ->
            if (responseData?.isBlocking() == true) {
                removeBlockingCount()
            } else {
                removeNonBlockingCount()
            }

            if (getBlockingCount() == 0) {
                setLoading(false)
            }

            onDataRecovery(responseData)
        }
    }

    private fun addBlockingCount() {
        m_iBlockingCount++
    }

    private fun removeBlockingCount() {
        m_iBlockingCount--
    }

    private fun addNonBlockingCount() {
        m_iNonBlockingCount++
    }

    private fun removeNonBlockingCount() {
        m_iNonBlockingCount--
    }

    private fun getBlockingCount(): Int {
        return m_iBlockingCount
    }

    private fun getNonBlockingCount(): Int {
        return m_iNonBlockingCount
    }

    private fun setLoading(bLoading: Boolean) {
        m_bLoading.value = bLoading
    }
}