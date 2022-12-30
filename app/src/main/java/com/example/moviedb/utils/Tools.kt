package com.example.moviedb.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.moviedb.model.MovieGenre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

object Tools {

    fun convertDateText(value: String, formatTo: String, formatFrom: String): String {
        return if (value.isNotEmpty()) {
            val fromFormat = SimpleDateFormat(formatFrom, Locale.getDefault())
            val date = fromFormat.parse(value)

            val calendarFormat = SimpleDateFormat(formatTo, Locale.getDefault())
            date?.let { calendarFormat.format(it) }.toString()
        } else {
            value
        }
    }

    fun saveData(context: Context, values: List<MovieGenre>?) {
        val sharedPreferences = context.getSharedPreferences("preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(values)
        editor.putString("genre_list", json)
        editor.apply()
    }

    fun loadData(context: Context): List<MovieGenre> {
        val sharedPreferences = context.getSharedPreferences("preferences", MODE_PRIVATE)
        val json = sharedPreferences.getString("genre_list", "").toString()
        val listType = object : TypeToken<List<MovieGenre>>() {}.type
        return Gson().fromJson(json, listType)
    }
}