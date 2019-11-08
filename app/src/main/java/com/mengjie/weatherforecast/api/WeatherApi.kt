package com.mengjie.weatherforecast.api

import com.mengjie.weatherforecast.model.WeatherDataList
import retrofit2.http.GET

interface WeatherApi {

    @GET("forecast?q=Singapore&units=metric&appid=b3bc9b80f3cc5f736df03d199bf85b0f")
    suspend fun getForecastWeather(): WeatherDataList

}