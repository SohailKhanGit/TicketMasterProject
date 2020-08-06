package com.example.ticketmasterproject.database


import androidx.room.TypeConverter
import com.example.ticketmasterproject.domain.EmbeddedVenues
import com.example.ticketmasterproject.network.dataclasses.EmbeddedVenuesContainer
import com.example.ticketmasterproject.network.dataclasses.Venues
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    //
//    @TypeConverter
//    fun fromEmbedded(value: Venues): List<Venues>{
//        val listType = object:TypeToken<List<Venues>>(){
//
//        }.type
//        return Gson().fromJson(value,listType)
//    }
//
//    @TypeConverter
//    fun toEmbedded(list: List<Venues>): Venues{
//        val gson = Gson()
//        return gson.toJson(list)
//    }
//    @TypeConverter
//    fun fromEmbedded():String{
//        return
//    }
//
//    @TypeConverter
//    fun toEmbedded(venues: Venues: EmbeddedVenuesContainer: ):
}