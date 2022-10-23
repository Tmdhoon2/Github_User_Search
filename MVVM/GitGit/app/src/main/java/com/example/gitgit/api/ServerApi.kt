package com.example.gitgit.api

import com.example.gitgit.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerApi {
    @GET("users/{userId}")
    suspend fun search(
        @Path("userId") id: String
    ) : Response<UserResponse>
}