package com.example.ticketmasterproject.network.service

import com.example.ticketmasterproject.network.dataclasses.NetworkEmbeddedContainer
import com.example.ticketmasterproject.network.dataclasses.NetworkEventsContainer
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("discovery/v2/events.json")
    fun getEventObj(@Query(Constants.API_KEY)apiKey : String?): Deferred<NetworkEmbeddedContainer>

}