package com.example.homework8.repository

import org.json.JSONObject

class FutureWeatherDataModelMapper : (String) -> List<FutureWeatherDataModel> {
    override fun invoke(jsonData: String): List<FutureWeatherDataModel> {
        val jsonObject = JSONObject(jsonData)
        val arrayObject = jsonObject.getJSONArray("list")
        if (arrayObject.length() != 0) {
            val itemList = mutableListOf<FutureWeatherDataModel>()
            for (index in 1 until arrayObject.length()) {
                val itemObject = arrayObject.getJSONObject(index)
                val time = itemObject.getString("dt_txt")
                val temp = itemObject.getJSONObject("main").getString("temp")
                val description = itemObject.getJSONArray("weather").getJSONObject(0).getString("main")
                val icon = itemObject.getJSONArray("weather").getJSONObject(0).getString("icon")
                itemList.add(FutureWeatherDataModel(time, temp, description, icon))
            }
            return itemList
        }
        return emptyList()
    }
}