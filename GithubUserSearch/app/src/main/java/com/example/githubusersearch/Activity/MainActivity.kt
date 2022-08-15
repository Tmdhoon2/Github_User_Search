package com.example.githubusersearch.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusersearch.Api.RetrofitBuilder
import com.example.githubusersearch.DTO.UserResponse
import com.example.githubusersearch.LikeDatabase
import com.example.githubusersearch.LikeEntity
import com.example.githubusersearch.LikeListActivity
import com.example.githubusersearch.R
import com.example.githubusersearch.Recyclerview.LikeAdapter
import com.example.githubusersearch.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    lateinit var db: LikeDatabase

    companion object{
        lateinit var preferences:SharedPreferences
        lateinit var editor: SharedPreferences.Editor
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = LikeDatabase.getInstace(this)!!

        preferences = getSharedPreferences("Favorite", MODE_PRIVATE)
        editor = preferences.edit()

        binding.ivwhite.bringToFront()

        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        binding.ivSearch.setOnClickListener(View.OnClickListener {

            RetrofitBuilder.api.user(binding.etuserId.text.toString()).enqueue(object : Callback<UserResponse> {

                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                    if(response.isSuccessful){
                        var response = response.body()!!

                        binding.ivlikelist.setOnClickListener {
                            val intent = Intent(applicationContext, LikeListActivity::class.java)
                            startActivity(intent)
                        }

                        binding.ivwhite.setImageResource(0)

                        if(preferences.getInt("Favorite" + response.login, 0) == 1){
                            binding.ivstar.setImageResource(R.drawable.ic_yellow_star)
                        }else if(preferences.getInt("Favorite" + response.login, 0) == 0){
                            binding.ivstar.setImageResource(R.drawable.ic_white_star)
                        }

                        binding.ivstar.setOnClickListener(View.OnClickListener {
                            if(preferences.getInt("Favorite" + response.login, 0) == 1){
                                binding.ivstar.setImageResource(R.drawable.ic_white_star)
                                editor.putInt("Favorite" + response.login, 0).commit()
                            }else if(preferences.getInt("Favorite" + response.login, 0) == 0){
                                binding.ivstar.setImageResource(R.drawable.ic_yellow_star)
                                editor.putString("Url", response.avatar_url).commit()
                                editor.putString("Name", response.login).commit()
                                editor.putInt("Favorite" + response.login, 1).commit()
                            }
                        })

                        Glide.with(applicationContext)
                            .load(response.avatar_url)
                            .into(binding.ivprofile)

                        binding.tvid.setText(response.login)
                        binding.tvname.setText(response.name)
                        binding.tvfollowers.setText(""+response.followers)
                        binding.tvfollowing.setText(""+response.following)
                        binding.tvdes.setText(response.bio)
                        binding.tvemail.setText(response.email)
                        binding.tvcompany.setText(response.company)

                        var input = response.created_at
                        var token = input.split('-')
                        var token1 = token[2].split('T')
                        binding.tvjoin.setText(token[0] + "-" + token[1] + "-" + token1[0])

                        binding.tvrepo.setText(""+response.public_repos)

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