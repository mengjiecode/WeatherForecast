package com.mengjie.weatherforecast

import com.mengjie.weatherforecast.utils.WeatherUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getDateString() {
        val actualValue = WeatherUtils.getDateString("2019-11-08 21:00:00")
        assertEquals("Fri, 8 Nov 2019, 21:00", actualValue)
    }
}
