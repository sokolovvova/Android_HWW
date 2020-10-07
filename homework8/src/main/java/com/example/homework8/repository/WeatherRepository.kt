package com.example.homework8.repository

import okhttp3.*
import java.io.IOException

private const val API_KEY = "0516b70d5f66a33f99764a5e0938b64f"

class CurrentWeatherRepository {
    private val okHttpClient = OkHttpClient()
    private val currentWeatherDataModelMapper: (String) -> CurrentWeatherDataModel = CurrentWeatherDataModelMapper()
    private val futureWeatherDataModelMapper: (String) -> List<FutureWeatherDataModel> = FutureWeatherDataModelMapper()
    fun refreshCurrentData(onCurrentDataReadyCallback: OnCurrentDataReadyCallback, units: String) {
        val url = "https://api.openweathermap.org/data/2.5/weather?q=Minsk&appid=$API_KEY&units=$units"
        val request = Request.Builder().url(url).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.string()?.let { currentWeatherDataModelMapper(it) }?.let {
                        val postfix = if (units == "metric") "C" else "F"
                        it.temp = it.temp.substringBefore(".") + postfix + 0x00B0.toChar()
                        it.weatherIcon = "ic_" + it.weatherIcon
                        onCurrentDataReadyCallback.onDataReady(it)
                    }
                }
            }
        })
    }

    fun refreshFutureData(onFutureDataReadyCallback: OnFutureDataReadyCallback, units: String) {
        val url = "https://api.openweathermap.org/data/2.5/forecast?q=Minsk&appid=$API_KEY&units=$units"
        val request = Request.Builder().url(url).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.string()?.let { futureWeatherDataModelMapper(it) }?.let {
                        val postfix = if (units == "metric") "C" else "F"
                        for (item in it) {
                            item.temp = item.temp.substringBefore(".") + postfix + 0x00B0.toChar()
                            item.icon = "ic_" + item.icon
                            item.time = item.time.subSequence(8, 16).toString()
                        }
                        onFutureDataReadyCallback.onDataReady(it)
                    }
                }
            }
        })
    }
}

interface OnCurrentDataReadyCallback {
    fun onDataReady(currentWeatherData: CurrentWeatherDataModel)
}

interface OnFutureDataReadyCallback {
    fun onDataReady(futureWeatherData: List<FutureWeatherDataModel>)
}