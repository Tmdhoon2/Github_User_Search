package com.example.githubusersearch


import android.content.Intent
import android.net.Uri
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

        binding.ivwhite.bringToFront()

        binding.ivSearch.setOnClickListener(View.OnClickListener {
            RetrofitBuilder.api.user(binding.etuserId.text.toString()).enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if(response.isSuccessful){
                        binding.ivwhite.setImageResource(0)

                        var response = response.body()!!

                        Glide.with(applicationContext)
                            .load(response.avatar_url)
                            .into(binding.ivprofile)

                        binding.tvid.setText(response.login)
                        binding.tvname.setText(response.name)
                        binding.tvfollowers.setText(""+response.followers)
                        binding.tvfollowing.setText(""+response.following)
                        binding.tvemail.setText(response.email)
                        binding.tvdes.setText(response.bio)

                        var input = response.created_at

                        var token = input.split('-')
                        var token1 = token[2].split('T')
                        binding.tvjoin.setText(token[0] + "-" + token[1] + "-" + token1[0])

                        binding.tvrepo.setText(""+response.public_repos)

                        binding.tvcompany.setText(response.company)

                        binding.btUrl.setOnClickListener(View.OnClickListener {
                            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(response.html_url))
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