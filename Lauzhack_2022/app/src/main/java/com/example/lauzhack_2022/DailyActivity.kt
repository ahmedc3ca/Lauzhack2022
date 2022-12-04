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
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class DailyActivity : AppCompatActivity() {

    lateinit var best_prod: TextView
    lateinit var best_foot: TextView
    lateinit var worst_prod: TextView
    lateinit var worst_foot: TextView
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
        worst_prod = findViewById(R.id.article2)
        worst_foot = findViewById(R.id.footprint2)
        day_text = findViewById(R.id.day)
        val storage = LocalSave.GetStorage(this)

        var best = Pair("N/A", 0.0)
        var worst = Pair("N/A", 0.0)
        if(storage != null){
            best = StorageManipulator.GetDayBest(storage, day)
            worst = StorageManipulator.GetDayWorst(storage, day)
        }

        day_text.text = day_string
        best_prod.text = best.first
        best_foot.text = best.second.toString() + " CO2eq"
        worst_prod.text = worst.first
        worst_foot.text = worst.second.toString() + " CO2eq"

        dashboard_btn = findViewById(R.id.dashboard_button)
        dashboard_btn.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

    }
}