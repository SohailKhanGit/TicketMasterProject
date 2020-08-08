//package com.example.ticketmasterproject.network.service
//
//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//
//
//
//object EventByteNetwork {
//
//
//    //LOGGER
//    private val logger =HttpLoggingInterceptor()
//        .setLevel(HttpLoggingInterceptor.Level.BODY)
//
//
//    //OK HTTP BUILDER
//    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(Constants.BASE_URL)
//        .addConverterFactory(MoshiConverterFactory.create())
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())
//        .client(okHttp.build())
//        .build()
//
//
//    val eventBytes = retrofit.create(Service::class.java)
//}