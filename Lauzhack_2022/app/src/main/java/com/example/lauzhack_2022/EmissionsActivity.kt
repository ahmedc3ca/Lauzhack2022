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
        var json1: String = "{\"co2\": [{\"item\": {\"name\": \"Aubergines\", \"value\": 61.41, \"prod_perc\": 74.90636704119851}}, {\"item\": {\"name\": \"Poivrons rouges\", \"value\": 531.38, \"prod_perc\": 78.47491437389439}}, {\"item\": {\"name\": \"Kiwis 500g\", \"value\": 63.62, \"prod_perc\": 73.0902232002515}}, {\"item\": {\"name\": \"Oignons rouges\", \"value\": 37.5, \"prod_perc\": 93.33333333333333}}, {\"item\": {\"name\": \"Valflora lait 1L\", \"value\": 162.385, \"prod_perc\": 97.87675435293701}}, {\"item\": {\"name\": \"Bananes\", \"value\": 40.91, \"prod_perc\": 41.55463211928624}}, {\"item\": {\"name\": \"Salsa dip douce\", \"value\": 180, \"prod_perc\": 75.23}}], \"date\": \"2022-12-01\"}"
        var json2: String = "{\"co2\": [{\"item\": {\"name\": \"Celeri-branche\", \"value\": 44.63, \"prod_perc\": 85.1445216222272}}, {\"item\": {\"name\": \"Salade 260g\", \"value\": 180, \"prod_perc\": 75.23}}, {\"item\": {\"name\": \"Pdt.rosti-frites\", \"value\": 543.35, \"prod_perc\": 98.64728075825894}}], \"date\": \"2022-11-28\"}"
        var json3: String = "{\"co2\": [{\"item\": {\"name\": \"Zweifel Graneo origin.\", \"value\": 28.81, \"prod_perc\": 62.47830614370011}}, {\"item\": {\"name\": \"Chou chinois\", \"value\": 29.88, \"prod_perc\": 83.66800535475234}}, {\"item\": {\"name\": \"Heidi Lait entier\", \"value\": 162.385, \"prod_perc\": 97.87675435293701}}, {\"item\": {\"name\": \"Pommes Gala Victor\", \"value\": 22.19, \"prod_perc\": 90.13068949977466}}], \"date\": \"2022-11-30\"}"
        val entry1 = JsonParser.JsonToStorageEntry(json1)
        if (entry1 != null) {
            LocalSave.InsertEntry(entry1, this)
        }
        val entry2 = JsonParser.JsonToStorageEntry(json2)
        if (entry2 != null) {
            LocalSave.InsertEntry(entry2, this)
        }
        val entry3 = JsonParser.JsonToStorageEntry(json3)
        if (entry3 != null) {
            LocalSave.InsertEntry(entry3, this)
        }
        if(json == null){
            json = ""
        }
        val entry = JsonParser.JsonToStorageEntry(json)
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