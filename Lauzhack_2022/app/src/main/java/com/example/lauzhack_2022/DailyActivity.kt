package com.example.lauzhack_2022

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.lauzhack_2022.Util.LocalSave
import com.example.lauzhack_2022.Util.StorageManipulator
import java.lang.Math.floor
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class DailyActivity : AppCompatActivity() {

    lateinit var best_prod: TextView
    lateinit var best_foot: TextView
    lateinit var best_perc: TextView
    lateinit var worst_prod: TextView
    lateinit var worst_foot: TextView
    lateinit var worst_perc: TextView
    lateinit var day_text: TextView
    lateinit var dashboard_btn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
        supportActionBar?.hide()

        val day_string = intent.getStringExtra("DAY")
        val day = LocalDate.parse(day_string)

        best_prod = findViewById(R.id.article1)
        best_foot = findViewById(R.id.footprint1)
        best_perc = findViewById(R.id.percent1)
        worst_prod = findViewById(R.id.article2)
        worst_foot = findViewById(R.id.footprint2)
        worst_perc = findViewById(R.id.percent2)
        day_text = findViewById(R.id.day)

        val storage = LocalSave.GetStorage(this)

        var best = StorageManipulator.GetDayBest(storage, day)
        var worst = StorageManipulator.GetDayWorst(storage, day)

        day_text.text = day_string
        best_prod.text = best.name
        best_foot.text = best.footprint.toString() + " g CO2eq/100g"
        best_perc.text = floor(best.prodperc).toString() + "% From Item Production"
        worst_prod.text = worst.name
        worst_foot.text = worst.footprint.toString() + "g CO2eq/100g"
        worst_perc.text = floor(worst.prodperc).toString() + "% From Item Production"


        dashboard_btn = findViewById(R.id.dashboard_button)
        dashboard_btn.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

    }
}