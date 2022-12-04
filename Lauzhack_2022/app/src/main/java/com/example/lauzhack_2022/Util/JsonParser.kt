package com.example.lauzhack_2022.Util

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.time.LocalDate

class JsonParser {

    data class JsonClass(var co2: List<JsonEntry>, var date: String)

    data class JsonEntry(var item: JsonArticle)

    data class JsonArticle(var name: String, var value: Double)

    companion object{
        @RequiresApi(Build.VERSION_CODES.O)
        fun JsonToStorageEntry(js: String): StorageEntry?{
            if (js == "") return null
            val gson = Gson()
            val savedClass = gson.fromJson(js, JsonParser.JsonClass::class.java)
            return StorageEntry(LocalDate.parse(savedClass.date), savedClass.co2.map { e:JsonEntry -> Article(e.item.name, e.item.value) })
        }
    }


}