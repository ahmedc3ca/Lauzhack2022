package com.example.lauzhack_2022

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter


class DashboardActivity : AppCompatActivity() {

    // on below line we are creating
    // variables for our bar chart
    lateinit var barChart: BarChart

    // on below line we are creating
    // a variable for bar data
    lateinit var barData: BarData

    // on below line we are creating a
    // variable for bar data set
    lateinit var barDataSet: BarDataSet

    // on below line we are creating array list for bar data
    lateinit var barEntriesList: ArrayList<BarEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        // on below line we are initializing
        // our variable with their ids.
        barChart = findViewById(R.id.chart)

        // on below line we are calling get bar
        // chart data to add data to our array list
        getBarChartData()

        // on below line we are initializing our bar data set
        barDataSet = BarDataSet(barEntriesList, "Bar Chart Data")

        // on below line we are initializing our bar data
        barData = BarData(barDataSet)

        // on below line we are setting data to our bar chart
        barChart.data = barData

        // on below line we are setting colors for our bar chart text
        barDataSet.valueTextColor = R.color.black

        // on below line we are setting color for our bar data set
        barDataSet.setColor(resources.getColor(R.color.green))

        // on below line we are setting text size
        barDataSet.valueTextSize = 16f
        // on below line we are enabling description as false
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false


        val xAxis: XAxis = barChart.getXAxis()
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.textSize = 10f
        xAxis.textColor = R.color.black
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.setDrawLabels(true)
        xAxis.labelCount = 7
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                // Return the desired label for the given value
                return when (value) {
                    0f -> "Monday"
                    1f -> "Tuesday"
                    2f -> "Wednesday"
                    3f -> "Thursday"
                    4f -> "Friday"
                    5f -> "Saturday"
                    6f -> "Sunday"
                    else -> ""
                }
            }
        }
        xAxis.setDrawAxisLine(false);

        val average = 3f
        val limitLine = LimitLine(average, "Average")
        limitLine.lineColor = R.color.kaki
        limitLine.textColor = R.color.kaki
        barChart.axisLeft.addLimitLine(limitLine)

    }

    private fun getBarChartData() {
        barEntriesList = ArrayList()

        // on below line we are adding data
        // to our bar entries list
        barEntriesList.add(BarEntry(0f, 1f))
        barEntriesList.add(BarEntry(1f, 2f))
        barEntriesList.add(BarEntry(2f, 3f))
        barEntriesList.add(BarEntry(3f, 4f))
        barEntriesList.add(BarEntry(4f, 5f))
        barEntriesList.add(BarEntry(5f, 1f))
        barEntriesList.add(BarEntry(6f, 2f))
    }

}