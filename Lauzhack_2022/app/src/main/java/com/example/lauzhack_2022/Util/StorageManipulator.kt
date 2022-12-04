package com.example.lauzhack_2022.Util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
@RequiresApi(Build.VERSION_CODES.O)
class StorageManipulator {
    companion object{

        fun GetWeekDates(): List<LocalDate>{
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

            return weekDates
        }

        fun GetWeekDay(day: Float): LocalDate{
            val weeks = GetWeekDates()
            return when (day) {
                0f -> weeks[0]
                1f -> weeks[1]
                2f -> weeks[2]
                3f -> weeks[3]
                4f -> weeks[4]
                5f -> weeks[5]
                6f -> weeks[6]
                else -> LocalDate.MIN
            }
        }

        fun GetTodayEmissions(storage: Storage): Double{
            val today = LocalDate.now()
            return GetDayEmissions(storage, today)
        }

        fun GetDayEmissions(storage: Storage, day: LocalDate): Double{

            val i = storage.entries.map { entry -> entry.date }.indexOf(day.toString());
            if(i == -1){
                return  0.0
            }else{
                return storage.entries.get(i).articles.map { a -> a.footprint }.sum()
            }
        }

        fun GetWeekEmission(storage: Storage): List<Double>{
            var weeks = GetWeekDates()
            return weeks.map { w -> GetDayEmissions(storage,w) }
        }


        fun GetDayBest(storage: Storage, day: LocalDate):Pair<String, Double>{
            val i = storage.entries.map { entry -> entry.date }.indexOf(day.toString());
            if(i == -1) {
                return Pair("N/A", 0.0)
            }else{
                val footprints = storage.entries.get(i).articles.map { a -> a.footprint }
                val best = footprints.min()
                val name = storage.entries.get(i).articles.get(footprints.indexOf(best)).name
                return Pair(name, best)
            }
        }

        fun GetDayWorst(storage: Storage, day: LocalDate):Pair<String, Double>{
            val i = storage.entries.map { entry -> entry.date }.indexOf(day.toString());
            if(i == -1) {
                return Pair("N/A", 0.0)
            }else{
                val footprints = storage.entries.get(i).articles.map { a -> a.footprint }
                val best = footprints.max()
                val name = storage.entries.get(i).articles.get(footprints.indexOf(best)).name
                return Pair(name, best)
            }
        }

    }
}