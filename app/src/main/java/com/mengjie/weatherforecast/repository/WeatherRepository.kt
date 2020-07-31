package com.mengjie.weatherforecast.repository


import com.mengjie.weatherforecast.service.WeatherApi
import com.mengjie.weatherforecast.data.WeatherDataList

class WeatherRepository(private val api: WeatherApi) {

    suspend fun getForecastWeather(): WeatherDataList {
        return api.getForecastWeather()
    }

}