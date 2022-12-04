package com.example.lauzhack_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lauzhack_2022.adapters.EmissionAdapter

class EmissionsActivity : AppCompatActivity() {

    private lateinit var recycler_view: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_emissions)

        supportActionBar?.hide()

        recycler_view = findViewById(R.id.recycler_view_items)
        // Set the LayoutManager that this RecyclerView will use.
        recycler_view.layoutManager = LinearLayoutManager(this)

        // Adapter class is initialized and list is passed in the param.
        val productAdapter = EmissionAdapter(this, generateDummynames(), generateDummynums())

        // adapter instance is set to the recyclerview to inflate the items.
        recycler_view.adapter = productAdapter
    }

    private fun generateDummynames(): ArrayList<String> {
        val list = ArrayList<String>()

        for(i in 1..15){
            list.add("Item $i")
        }

        return list
    }

    private fun generateDummynums(): ArrayList<Int> {
        val list = ArrayList<Int>()

        for(i in 1..15){
            list.add(i)
        }

        return list
    }
}