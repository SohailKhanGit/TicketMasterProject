package com.example.ticketmasterproject.di


import com.example.ticketmasterproject.network.service.Constants

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val netModule = module{

    fun provideLogger(): HttpLoggingInterceptor{
        val logger = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return logger
    }

    fun provideOkHttpBuilder(logger:HttpLoggingInterceptor): OkHttpClient{
        val okHttp = OkHttpClient.Builder()
            .addInterceptor(logger)
        return okHttp.build()
    }

    fun provideRetrofit( okHttp: OkHttpClient): Retrofit{
       return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttp)
            .build()
    }

    single { provideLogger() }
    single { provideOkHttpBuilder(get()) }
    single { provideRetrofit(get())}


}