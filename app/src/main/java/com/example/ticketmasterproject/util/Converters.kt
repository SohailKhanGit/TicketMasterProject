package com.example.ticketmasterproject.util


import androidx.room.TypeConverter
import com.example.ticketmasterproject.domain.EmbeddedVenues
import com.example.ticketmasterproject.domain.Image
import com.example.ticketmasterproject.network.dataclasses.EmbeddedVenuesContainer
import com.example.ticketmasterproject.network.dataclasses.Venues
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class Converters {

//    @TypeConverter
//    fun listToJson(value: List<Image>?):String = Gson().toJson(value)
//
//    @TypeConverter
//    fun jsonToList(value: String):List<Image> = Gson().fromJson(value, Array<Image>::class.java).toList()

//    @TypeConverter
//    fun imgToString(imageList: List<Image>): String = Gson().toJson(imageList)
//
//    @TypeConverter
//    fun stringToImg(string: String): Image = Gson().fromJson(string, Image::class.java)



    @TypeConverter
    fun fromString(value: String): List<Image> {
        val listType = object : TypeToken<List<Image>>() {
        }.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun fromArrayList(list: List<Image>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}

