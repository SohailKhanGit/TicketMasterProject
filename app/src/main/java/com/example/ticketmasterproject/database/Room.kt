package com.example.ticketmasterproject.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


//https://app.ticketmaster.com/discovery/v2/events.json?apikey=RxMCDwe6dk1YATLdXtoeER9K1MsX6ZT8

@Dao
interface EventsDao{
    @Query("select * from databaseEvents")
    fun getEvents(): LiveData<List<DatabaseEvents>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(events: List<DatabaseEvents>)

}
@Database(entities = [DatabaseEvents::class], version = 2,exportSchema = false)
abstract class EventsDatabase: RoomDatabase(){
    abstract val eventsDao: EventsDao
}



private lateinit var INSTANCE: EventsDatabase

fun getDatabase(context : Context): EventsDatabase {
    synchronized(EventsDatabase::class.java) {
        if(!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                EventsDatabase::class.java,"results")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}