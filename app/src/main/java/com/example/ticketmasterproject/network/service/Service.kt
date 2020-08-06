package com.example.ticketmasterproject.network.service

import com.example.ticketmasterproject.network.dataclasses.NetworkEmbeddedContainer
import com.example.ticketmasterproject.network.dataclasses.NetworkEventDetails
import com.example.ticketmasterproject.network.dataclasses.NetworkEventsContainer
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
//https://app.ticketmaster.com/discovery/v2/events.json?apikey=RxMCDwe6dk1YATLdXtoeER9K1MsX6ZT8

    @GET("discovery/v2/events.json?apikey=RxMCDwe6dk1YATLdXtoeER9K1MsX6ZT8")
    fun getEventObj(): Deferred<NetworkEmbeddedContainer>

   // @Query(Constants.API_KEY)apikey : String?
//NetworkEventDetails
//@GET("discovery/v2/events.json")
//fun getEventObj(@Query(Constants.API_KEY)apikey : String?): Deferred<NetworkEventDetails>

    //get event by id https://app.ticketmaster.com/discovery/v2/events/Z7r9jZ1AeOdbU.json?apikey=RxMCDwe6dk1YATLdXtoeER9K1MsX6ZT8

}
