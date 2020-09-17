package com.example.ticketmasterproject.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.ticketmasterproject.database.EventsDatabase
import com.example.ticketmasterproject.repository.EventsRepository
import timber.log.Timber
import java.lang.Exception

class RefreshDataWorker(appContext: Context, params: WorkerParameters, private val database: EventsDatabase, private val repository: EventsRepository): CoroutineWorker(appContext, params){


    companion object{
        const val WORK_NAME = "com.example.android.devbyteviewer.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        try{
            repository.refreshEvents()
            Timber.d("WorkManager: Work request for sync is run")
        }catch (e:Exception){
            return Result.retry()
        }
        return  Result.success()
    }
}