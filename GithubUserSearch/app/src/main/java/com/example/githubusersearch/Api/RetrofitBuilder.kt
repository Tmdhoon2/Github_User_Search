package com.example.githubusersearch.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    var BASE_URL = "https://api.github.com/"
    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var api = retrofit.create(ServerApi::class.java)
}