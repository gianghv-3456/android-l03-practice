package com.example.practiceapp.data

import android.content.Context
import com.example.practiceapp.model.Contact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object ContactReader {
    fun readContactsFromAssets(context: Context): List<Contact> {
        val jsonString: String
        try {
            jsonString = context.assets.open("contacts.json").bufferedReader().use { it.readText() }
            val listContactType = object : TypeToken<List<Contact>>() {}.type
            return Gson().fromJson(jsonString, listContactType)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }
    }
}