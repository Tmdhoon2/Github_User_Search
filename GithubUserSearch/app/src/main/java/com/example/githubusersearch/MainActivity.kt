package com.example.githubusersearch


import android.content.Intent
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
                        var response = response.body()!!

                        Glide.with(applicationContext)
                            .load(response.avatar_url)
                            .into(binding.rvprofile)

                        binding.rvId.setText(response.login)

                        binding.cardview.setOnClickListener(View.OnClickListener {
                            val intent = Intent(applicationContext, UserInfoActivity::class.java)

                            intent.putExtra("Id", response.login)
                            intent.putExtra("Followers", response.followers)
                            intent.putExtra("Following", response.following)
                            intent.putExtra("Profile", response.avatar_url)
                            intent.putExtra("Bio", response.bio)

                            startActivity(intent)
                        })
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                }

            })
        })
    }
}