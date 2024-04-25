package com.example.compouseuiapplication.util

import android.content.Context
import com.example.compouseuiapplication.api.RequestData
import org.json.JSONObject

object UrlUtil {
    const val WS_CMD_GET_SAMPLE_DATA = "data"

    fun newGetRequestObj(context: Context,
                         strCmd: String,
                         joBody: JSONObject,
                         bBlocking: Boolean): RequestData {
        return RequestData(context,
                           strCmd,
                           "https://api.nationalize.io",
                           RequestData.REQ_METHOD_GET,
                           joBody,
                           bBlocking)
    }

    fun packReqQueryGetSample(name: String): JSONObject {
        val joRet = JSONObject()

        try {
            joRet.put("name", name)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return joRet
    }
}