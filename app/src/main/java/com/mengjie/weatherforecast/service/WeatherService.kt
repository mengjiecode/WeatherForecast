package com.mengjie.weatherforecast.service

import com.mengjie.weatherforecast.data.WeatherDataList
import retrofit2.http.GET

interface WeatherService {

    @GET("forecast?q=Singapore&units=metric&appid=b3bc9b80f3cc5f736df03d199bf85b0f")
    suspend fun getForecastWeather(): WeatherDataList

}