package com.example.lauzhack_2022

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    val clientId = "vrfO9IBm2TX5kNJBwHYmHJFOxlaJ2ySRwxnxv0z"
    val clientSecret =
        "YgCIRaXCJA27HFyUSfAD9l0iLDIZz7hp9ebJpL73OcYKAkYlpPEUTG3ABZukEIWLxuKBWiBH0GXtI7kWXB8GVFABCrVn1CbRo2w8wKDsI2rNKwtTOW65J52lIERvBzYe"
    val username = "thomas21"
    val apiKey = "3c8490153aab8570ab6e31c8832fdacd"

    private val cameraRequest = 1888
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toCamera = findViewById<Button>(R.id.camera_button)
        toCamera.setOnClickListener {
            val myIntent = Intent(this, CaptureActivity::class.java)
            this.startActivity(myIntent)
        }


//        val client = VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey)
//        val fileName = "example.jpg"
//        client.processDocument(assets.open(fileName), fileName,
//            emptyList(), false, null, { jsonString ->
//                //Update UI with jsonString response
//
//            }, { errorMessage ->
//                //handle errorMessage
//            })
    }

}




