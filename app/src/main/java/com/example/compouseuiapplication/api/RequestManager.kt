package com.example.compouseuiapplication.api

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.compouseuiapplication.util.HttpUtil

class RequestManager {
    companion object {
        private const val MESSAGE_DATA_RECOVERY = 0
    }

    fun postData(requestData: RequestData, onDataRecovery: OnDataRecovery) {
        Thread {
            val responseData: ResponseData? = HttpUtil.postData(requestData)
            val msg = Message()
            msg.what = MESSAGE_DATA_RECOVERY
            msg.obj = ResponseInfo(responseData, onDataRecovery)

            m_handler.sendMessage(msg)
        }.start()
    }

    private val m_handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if (msg.what == MESSAGE_DATA_RECOVERY) {
                val responseInfo: ResponseInfo = msg.obj as ResponseInfo

                responseInfo.onDataRecovery(responseInfo.responseData)
            }
        }
    }
}
data class ResponseInfo(val responseData: ResponseData?, val onDataRecovery: OnDataRecovery)
typealias OnDataRecovery = (responseData: ResponseData?) -> Unit