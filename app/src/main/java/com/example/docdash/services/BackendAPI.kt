package com.example.docdash.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendAPI {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    val backendAPI: BackendService by lazy {
        retrofit.create(BackendService::class.java)
    }
}
