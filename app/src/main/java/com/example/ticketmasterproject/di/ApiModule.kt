package com.example.ticketmasterproject.di

import com.example.ticketmasterproject.network.service.Service
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module{
    fun provideApi(retrofit: Retrofit): Service{
        return retrofit.create(Service::class.java)
    }
    single { provideApi(get()) }
}