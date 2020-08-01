package com.mengjie.weatherforecast.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
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

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}