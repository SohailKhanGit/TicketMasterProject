package com.example.ticketmasterproject.network.dataclasses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dates (
    var start: Start,
    var timezone: String)

@JsonClass(generateAdapter = true)
data class Start(
    var localData: String,
    var localTime: String)