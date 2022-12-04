package com.example.lauzhack_2022

import android.content.Intent
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lauzhack_2022.Util.JsonParser
import com.example.lauzhack_2022.Util.LocalSave
import com.example.lauzhack_2022.adapters.EmissionAdapter

class EmissionsActivity : AppCompatActivity() {

    private lateinit var recycler_view: RecyclerView
    private lateinit var dashboard_btn: ImageButton
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_emissions)

        supportActionBar?.hide()

        recycler_view = findViewById(R.id.recycler_view_items)
        // Set the LayoutManager that this RecyclerView will use.
        recycler_view.layoutManager = LinearLayoutManager(this)

        // Adapter class is initialized and list is passed in the param.

        var json : String? = intent.getStringExtra("JSON")
        //var json : String = "{\"co2\": [{\"item\": {\"name\": \"MBud Tranche de poulet\", \"value\": 447.58, \"prod_perc\": 97.16898071121807}}, {\"item\": {\"name\": \"6 Oeufs\", \"value\": 220.26, \"prod_perc\": 98.06592209207301}}, {\"item\": {\"name\": \"El Sombr. tortilla big\", \"value\": 100, \"prod_perc\": 87.65}}, {\"item\": {\"name\": \"Gingembre\", \"value\": 54.36, \"prod_perc\": 55.18763796909492}}, {\"item\": {\"name\": \"MClass MSC thon eau 6x\", \"value\": 413.87, \"prod_perc\": 86.98383550390218}}, {\"item\": {\"name\": \"Avocats mures\", \"value\": 93.38, \"prod_perc\": 58.149496680231316}}, {\"item\": {\"name\": \"Monster ultra 355ml\", \"value\": 170, \"prod_perc\": 81.23}}], \"date\": \"2022-12-04\"}"
        if(json == null){
            json = ""
        }
        val entry = JsonParser.JsonToStorageEntry(json)
        Log.i("STORAGE", entry.toString())
        if(entry != null){
            var names = entry.articles.map { e -> e.name }
            var footprints = entry.articles.map { e -> e.footprint }
            val productAdapter = EmissionAdapter(this, names, footprints)
            LocalSave.InsertEntry(entry, context = this)
            // adapter instance is set to the recyclerview to inflate the items.
            recycler_view.adapter = productAdapter
        }

        dashboard_btn = findViewById(R.id.dashboard_button)
        dashboard_btn.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

    }

}