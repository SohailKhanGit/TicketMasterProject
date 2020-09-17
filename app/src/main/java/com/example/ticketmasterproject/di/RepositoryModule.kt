package com.example.ticketmasterproject.di

import com.example.ticketmasterproject.database.EventsDao
import com.example.ticketmasterproject.database.EventsDatabase
import com.example.ticketmasterproject.network.service.Service
import com.example.ticketmasterproject.repository.EventsRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideRepository(api: Service, database: EventsDatabase):EventsRepository{
        return EventsRepository(api,database)
    }

    single { provideRepository(get(),get()) }
}