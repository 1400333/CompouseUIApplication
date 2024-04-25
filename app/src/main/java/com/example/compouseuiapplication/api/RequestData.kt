package com.example.compouseuiapplication.api

import android.content.Context
import org.json.JSONObject

data class RequestData(val context: Context,
                       val strCmd: String,
                       val strUrl: String,
                       val iReqMethod: Int,
                       val joBody: JSONObject?,
                       val bBlocking: Boolean) {
    companion object {
        const val REQ_METHOD_GET = 0
    }

    fun constructResp(iHttpStatus: Int, joResp: JSONObject?): ResponseData {
        val responseData: ResponseData = ResponseData.constructResp(iHttpStatus, joResp)

        responseData.setRequestData(this)

        return responseData
    }

    fun constructConnTimeoutResp(): ResponseData {
        val responseData: ResponseData = ResponseData.constructConnTimeoutResp()

        responseData.setRequestData(this)

        return responseData
    }

    fun constructConnErrorResp(iHttpStatus: Int): ResponseData {
        val responseData: ResponseData = ResponseData.constructConnErrorResp(iHttpStatus)

        responseData.setRequestData(this)

        return responseData
    }

    fun isGetMethod(): Boolean {
        return iReqMethod == REQ_METHOD_GET
    }

    fun isBlocking(): Boolean {
        return bBlocking
    }
}