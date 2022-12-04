package com.example.lauzhack_2022.Util

import android.content.Context
import android.os.Build
import android.os.Debug
import android.util.DebugUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager
import com.google.gson.Gson

class LocalSave {

    companion object{

        private val KEY_CLASS = "STORAGE"
        fun GetStorage(context: Context): Storage?{
            // Get a reference to the SharedPreferences object
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            // Create a Gson instance
            val gson = Gson()
            // Retrieve the saved custom class
            val json = preferences.getString(KEY_CLASS, "")
            if (json != null) {

            }
            var savedClass = gson.fromJson(json, Storage::class.java)

            return savedClass
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun InsertEntry(entry: StorageEntry, context: Context){
            var storage = GetStorage(context)
            if(storage == null){
                storage = Storage(listOf(entry))
            }else{

                val i = storage.entries.map{ e -> e.date }.indexOf(entry.date)
                if(i == -1){
                    if(storage.entries == null){
                        storage = Storage(listOf(entry))
                    }
                    storage.entries = storage.entries + entry
                }else{
                    val existing_entry = storage.entries.get(i)
                    val new_articles = existing_entry.articles + entry.articles
                    storage.entries.get(i).articles = new_articles
                }
            }
            StoreStorage(context, storage)
        }

        fun StoreStorage(context: Context, storage: Storage){
            Log.i("STORAGE", storage.toString())
            // Get a reference to the SharedPreferences object
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            // Create a Gson instance
            val gson = Gson()
            val json = gson.toJson(storage)
            preferences.edit().putString(KEY_CLASS, json).apply()
        }
    }


}