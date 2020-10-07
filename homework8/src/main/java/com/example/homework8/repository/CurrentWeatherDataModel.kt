package com.example.homework8.repository

data class CurrentWeatherDataModel(
        val cityName: String,
        val weatherDescription: String,
        var temp: String,
        var weatherIcon: String
)