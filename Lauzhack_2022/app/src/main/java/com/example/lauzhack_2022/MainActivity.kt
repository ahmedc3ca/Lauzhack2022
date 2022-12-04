package com.example.lauzhack_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val ahmedbutton = findViewById<Button>(R.id.ahmed_button)
        ahmedbutton.setOnClickListener {
            val intent = Intent(this, EmissionsActivity::class.java)
            startActivity(intent)
        }
    }
}