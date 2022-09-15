package com.example.sadlfj.model

import android.util.Log
import com.example.sadlfj.api.ApiProvider
import com.example.sadlfj.view.MainActivity
import com.example.sadlfj.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(val mainViewModel: MainViewModel) {
    fun getUser(id: String) {
        ApiProvider.retrofit.getUserId(id).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    Log.d("Test", "UserRepository : getUser successful")
                    mainViewModel.setUser(response.body()!!)
                    mainViewModel.setProfile()
                    MainActivity.editor.putString("url", response.body()!!.avatar_url).commit()

                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("Test", "UserRepository : getUser fail")
            }
        })
    }
}
