package com.example.compouseuiapplication.api

import org.json.JSONObject

class ResponseData {
    private var m_iHttpStatus = -1
    private var m_requestData: RequestData? = null
    var m_joRespData: JSONObject? = null
    private var m_iApiCode = 0

    companion object {
        private const val API_CODE_CONNECTION_TIMEOUT = 8999
        private const val API_CODE_CONNECTION_ERROR = 8998
        private const val API_CODE_SUCCESS = 200
        fun constructResp(iHttpStatus: Int, joResp: JSONObject?): ResponseData {
            val ret = ResponseData()

            ret.setApiCode(API_CODE_SUCCESS)
            ret.setHttpStatus(iHttpStatus)
            ret.setRespBody(joResp)

            return ret
        }

        fun constructConnTimeoutResp(): ResponseData {
            val ret = ResponseData()

            ret.setApiCode(API_CODE_CONNECTION_TIMEOUT)

            return ret
        }

        fun constructConnErrorResp(iHttpStatus: Int): ResponseData {
            val ret = ResponseData()

            ret.setHttpStatus(iHttpStatus)
            ret.setApiCode(API_CODE_CONNECTION_ERROR)

            return ret
        }
    }

    fun setHttpStatus(iHttpStatus: Int) {
        m_iHttpStatus = iHttpStatus
    }

    fun setApiCode(iApiCode: Int) {
        m_iApiCode = iApiCode
    }

    fun setRespBody(joBody: JSONObject?) {
        m_joRespData = joBody
    }

    fun setRequestData(requestData: RequestData) {
        m_requestData = requestData
    }

    fun isBlocking(): Boolean {
        return m_requestData?.bBlocking ?: false
    }

}