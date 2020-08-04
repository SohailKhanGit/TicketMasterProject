package com.example.ticketmasterproject.network.dataclasses

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Venues (
    var name: String,
    var venueUrl: String,
    var location: Location
)

@JsonClass(generateAdapter = true)
data class Location(
    var longitude: String,
    var latitude: String
)