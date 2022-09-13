package com.example.sadlfj.api

import com.example.sadlfj.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerApi {
    @GET("users/{userId}")
    fun getUserId(
        @Path("userId") id: String
    ) : Call<UserResponse>
}