package com.example.sadlfj.model

import com.example.sadlfj.presenter.Presenter
import com.example.sadlfj.api.ApiProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository (val presenter: Presenter){
    fun getUser(id: String){
        ApiProvider.retrofit.getUserId(id).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    presenter.setUser(response.body()!!)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

            }

        })
    }

}