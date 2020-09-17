package com.example.ticketmasterproject.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.ticketmasterproject.database.EventsDao
import com.example.ticketmasterproject.database.EventsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module{


    fun provideDatabase(application: Application): EventsDatabase {
        return Room.databaseBuilder(application, EventsDatabase::class.java,"events")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(database: EventsDatabase):EventsDao{
        return database.eventsDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }

}