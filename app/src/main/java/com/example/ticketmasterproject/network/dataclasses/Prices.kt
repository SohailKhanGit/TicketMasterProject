package com.example.ticketmasterproject.network.dataclasses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PriceRange (
    var type: String,
    var currency: String,
    var min:String,
    var max:String
)
