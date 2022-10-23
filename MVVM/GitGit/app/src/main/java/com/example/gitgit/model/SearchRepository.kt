package com.example.gitgit.model

import com.example.gitgit.api.ApiProvider
import retrofit2.Response

class SearchRepository() {

    suspend fun search(userId : String) : Response<UserResponse> {
        return ApiProvider.retrofit.search(userId)
    }

}
