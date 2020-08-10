package com.example.ticketmasterproject

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.work.*
import com.example.ticketmasterproject.background.RefreshDataWorker
import com.example.ticketmasterproject.di.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import java.util.concurrent.TimeUnit

class App: Application() {
    private val appScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(listOf(netModule, apiModule, databaseModule, repositoryModule, viewModelModule))

            delayedInit()
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    private fun delayedInit() {
        Timber.i("Work has been scheduled")
        appScope.launch {
            Timber.plant(Timber.DebugTree())
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }
            .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        Timber.d("WorkManager: Periodic Work request for sync is scheduled")
        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }
}