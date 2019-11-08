package com.mengjie.weatherforecast.utils

import java.text.SimpleDateFormat

object WeatherUtils {

    /**
     * dateText example: "2019-11-08 21:00:00"
     */
    fun getDateString(dateText: String): String{
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH)
        val date = sdf.parse(dateText)
        sdf.applyPattern("EEE, d MMM yyyy, HH:mm")
        return sdf.format(date)
    }
}