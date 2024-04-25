package com.example.compouseuiapplication.data

import org.json.JSONObject

data class CountryData(val joData: JSONObject?) {
    companion object {
        private const val JO_KEY_COUNTRY_ID = "country_id"
        private const val JO_KEY_PROBABILITY = "probability"
    }

    fun getCountryId(): String {
        return joData?.optString(JO_KEY_COUNTRY_ID) ?: ""
    }

    fun getProbability(): Double {
        return joData?.optDouble(JO_KEY_PROBABILITY) ?: 0.0
    }
}
