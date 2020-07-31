package com.mengjie.weatherforecast.data

data class Result(
    val success: String?,
    val error: ErrorInformation?
)

data class ErrorInformation(
    val code: String?,
    val type: String?,
    val info: String?
)
