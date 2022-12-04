package com.example.lauzhack_2022


import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.lauzhack_2022.databinding.ActivityEditBinding
import okhttp3.OkHttpClient
import java.util.Base64.getEncoder
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.lauzhack_2022.Util.StorageManipulator
import com.veryfi.android.VeryfiClientFactory
import okhttp3.*
import java.io.*
import java.util.*

class EditActivity : AppCompatActivity() {
    val TAG = "EDIT_TAG"
    val URI_KEY = "URI_KEY"
    private val client = OkHttpClient()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val uri = intent.getParcelableExtra<Uri>(URI_KEY) //getParcelableExtra(URI_KEY)!!
        Log.d(TAG, uri.toString())
        binding.viewReceiptImage.setImageURI(uri)

        val toCamera = findViewById<Button>(R.id.new_take)
        toCamera.setOnClickListener { backToCapture() }

        val toLearn = findViewById<Button>(R.id.ok)
        toLearn.setOnClickListener{ backToMenu(uri) }



    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun backToMenu(uri: Uri?){
        val resolver = contentResolver
        val stream = uri?.let { resolver.openInputStream(it) }
        if (stream != null) {
            run("http://128.179.151.225:5000", stream)
        }
//        val myIntent = Intent(this, MainActivity::class.java)
//        this.startActivity(myIntent)
    }

    private fun backToCapture(){
        val myIntent = Intent(this, CaptureActivity::class.java)
        this.startActivity(myIntent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun run(url: String, stream: InputStream) {
        var base64EncodedString: String? = ""
        try {
            base64EncodedString = Base64.getEncoder().encodeToString(stream.readBytes())
//            Log.i("TAG", base64EncodedString)
        } catch (e: IOException) {
            e.message?.let { Log.e("TAG", it) }
        }

        val data = "{\"receipt\":\"".plus(base64EncodedString).plus("\"}")


        val request = Request.Builder()
            .url(url).post(RequestBody.create(MediaType.parse("application/json"), data))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("TAG", "hi")
                throw e
            }
            override fun onResponse(call: Call, response: Response) {
                response.body()?.string()?.let { Log.d(TAG,it); StartEmissionActivity(it) }
//                response.body()?.string()?.let { Log.d(TAG,it) }
            }
        })
    }

    private fun StartEmissionActivity(json: String){
        val intent = Intent(this, EmissionsActivity::class.java)
        intent.putExtra("JSON", json)
        startActivity(intent)
    }



}
