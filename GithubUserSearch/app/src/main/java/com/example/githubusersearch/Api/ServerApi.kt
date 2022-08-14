package com.example.githubusersearch.Api

import com.example.githubusersearch.DTO.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerApi {

    @GET("users/{userId}")
    fun user(
        @Path("userId") userId: String
    ): Call<UserResponse>

}