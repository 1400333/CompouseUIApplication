package com.example.compouseuiapplication.util

import android.text.TextUtils
import com.example.compouseuiapplication.api.RequestData
import com.example.compouseuiapplication.api.ResponseData
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object HttpUtil {
    private const val CONNECTION_TIMEOUT_MS: Long = 35 * 1000
    private const val CONNECTION_READ_WRITE_TIMEOUT_MS: Long = 35 * 1000
    private val JSON: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()

    fun getDataWithOkHttp(requestData: RequestData): ResponseData? {
        var respData: ResponseData? = null

        try {
            val urlBuilder: HttpUrl.Builder? = requestData.strUrl.toHttpUrlOrNull()?.newBuilder()

            // add query parameters
            val iterator: Iterator<String>? = requestData.joBody?.keys()
            var strKey: String
            var strValue: String

            while (iterator?.hasNext() == true) {
                strKey = iterator.next()
                strValue = requestData.joBody.optString(strKey)
                urlBuilder?.addEncodedQueryParameter(strKey, strValue)
            }

            val strURL = urlBuilder?.build().toString()
            val reqBuilder: Request.Builder = Request.Builder().url(strURL)

            val client: OkHttpClient =
                OkHttpClient.Builder().connectTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                    .readTimeout(CONNECTION_READ_WRITE_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                    .writeTimeout(CONNECTION_READ_WRITE_TIMEOUT_MS, TimeUnit.MILLISECONDS).build()

            val response = client.newCall(reqBuilder.build()).execute()
            val strResp = response.body?.string()

            if (TextUtils.isEmpty(strResp)) {
                respData = requestData.constructConnErrorResp(response.code)
            } else if (strResp?.get(0) == '{') {
                respData = requestData.constructResp(response.code, JSONObject(strResp))
            } else {
                respData = requestData.constructConnErrorResp(response.code)
            }

        } catch (e: Exception) {
            e.printStackTrace()

            if (respData == null) {
                respData = requestData.constructConnTimeoutResp()
            }
        }

        return respData
    }

    fun postData(requestData: RequestData): ResponseData? {
        if (requestData.isGetMethod()) {
            return getDataWithOkHttp(requestData)
        }

        return null
    }

}