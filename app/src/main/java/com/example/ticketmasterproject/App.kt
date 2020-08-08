package com.example.ticketmasterproject

import android.app.Application
import android.content.Context
import com.example.ticketmasterproject.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(listOf(netModule, apiModule, databaseModule, repositoryModule, viewModelModule))
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}