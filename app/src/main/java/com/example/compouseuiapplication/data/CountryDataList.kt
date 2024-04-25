package com.example.compouseuiapplication.data

import org.json.JSONArray
import org.json.JSONObject

data class CountryDataList(var jaData: JSONArray?) {
    companion object {
        private const val JO_KEY_COUNTRY = "country"

        fun parseCountryList(joData: JSONObject?): CountryDataList {
            return CountryDataList(joData?.optJSONArray(JO_KEY_COUNTRY))
        }
    }

    private fun getCountryCount(): Int {
        return jaData?.length() ?: 0
    }

    private fun getCountryByIndex(iIndex: Int): CountryData? {
        if (iIndex < 0 || iIndex >= getCountryCount()) {
            return null
        }

        val joData: JSONObject = jaData?.optJSONObject(iIndex) ?: return null

        return CountryData(joData)
    }

    fun getCountryList(): List<CountryData> {
        val dataList: MutableList<CountryData> = mutableListOf()
        val iCnt = getCountryCount()

        for (i in 0 until iCnt) {
            getCountryByIndex(i)?.let { countryData ->
                dataList.add(countryData)
            }
        }

        return dataList.toList()
    }
}
