package com.example.ticketmasterproject.util


import androidx.room.TypeConverter
import com.example.ticketmasterproject.domain.EmbeddedVenues
import com.example.ticketmasterproject.domain.Image
import com.example.ticketmasterproject.network.dataclasses.EmbeddedVenuesContainer
import com.example.ticketmasterproject.network.dataclasses.Venues
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun listToJson(value: List<Image>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Image>::class.java).toList()
}