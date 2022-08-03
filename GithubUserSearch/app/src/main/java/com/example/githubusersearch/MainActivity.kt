package com.example.githubusersearch

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.githubusersearch.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivSearch.setOnClickListener(View.OnClickListener {
            RetrofitBuilder.api.user(binding.etuserId.text.toString()).enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if(response.isSuccessful){
                        Glide.with(applicationContext)
                            .load(response.body()!!.avatar_url)
                            .into(binding.ivprofile)
                        binding.tvlogin.setText(response.body()!!.login).toString()
                        binding.tvfollowers.setText(""+response.body()!!.followers).toString()
                        binding.tvfollowing.setText(""+response.body()!!.following).toString()
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                }

            })
        })
    }
}