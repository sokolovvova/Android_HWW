package com.example.homework8.repository

import org.json.JSONObject

class CurrentWeatherDataModelMapper : (String) -> CurrentWeatherDataModel {
    override fun invoke(jsonData: String): CurrentWeatherDataModel {
        val jsonObject = JSONObject(jsonData)
        val cityName = jsonObject.getString("name")
        val weatherDescription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description")
        val temp = jsonObject.getJSONObject("main").getString("temp")
        val icon = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon")
        return CurrentWeatherDataModel(cityName, weatherDescription, temp, icon)
    }
}