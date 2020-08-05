package com.example.ticketmasterproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.ticketmasterproject.database.EventsDatabase
import com.example.ticketmasterproject.database.asDomainModel
import com.example.ticketmasterproject.domain.Events
import com.example.ticketmasterproject.network.dataclasses.asDatabaseModel
import com.example.ticketmasterproject.network.service.Constants
import com.example.ticketmasterproject.network.service.EventByteNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class EventsRepository(private val database:EventsDatabase){

    suspend fun refreshEvents(){
        withContext(Dispatchers.IO){
            Timber.d("Refresh events database called")
            val events = EventByteNetwork.eventBytes.getEventObj().await()
            database.eventsDao.insertAll(events.asDatabaseModel())
        }
    }

    val events: LiveData<List<Events>> = Transformations.map(database.eventsDao.getEvents()){
        it.asDomainModel()
    }




}