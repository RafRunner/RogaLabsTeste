package com.example.rogalabsteste.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun buildRetrofit(baseUrl: String = "https://jsonplaceholder.typicode.com/"): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

val retrofit = buildRetrofit()
