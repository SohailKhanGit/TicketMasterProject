package com.example.ticketmasterproject.network.dataclasses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Promoter (
    var id:String,
    var name: String,
    var description: String
)
