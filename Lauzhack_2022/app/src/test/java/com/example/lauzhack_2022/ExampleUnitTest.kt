package com.example.lauzhack_2022

import com.example.lauzhack_2022.Util.JsonParser
import com.example.lauzhack_2022.Util.Storage
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

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
    fun testGson(){
        val js:String = "{\"co2\": [{\"item\":{\"name\": \"MBud Tranche de poulet\", \"value\": 447.58}}, {\"item\":{\"name\": \"6 Oeufs\" ,\"value\": 220.26}} ], \"date\": \"2022-12-04\"}"
        val gson = Gson()
        val savedClass = gson.fromJson(js, JsonParser.JsonClass::class.java)
        println(savedClass.co2)
        println(savedClass.date)
    }

    @Test
    fun dateTest(){
        // Get the current date and adjust it to the beginning of the week
        val today = LocalDate.now()
        val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

        // Create a list to store the LocalDate objects for the week
        val weekDates = mutableListOf<LocalDate>()

        // Iterate over the days of the week and create LocalDate objects for each day
        for (i in 0 until 7) {
            val date = startOfWeek.plusDays(i.toLong())
            weekDates.add(date)
        }

        // Print the LocalDate objects for the week
        println(weekDates)
    }
}