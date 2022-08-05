package com.example.githubusersearch


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
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

        val preferences:SharedPreferences = getSharedPreferences("Favorite", MODE_PRIVATE)
        val editor = preferences.edit()

        var i:Int = 0

        binding.ivwhite.bringToFront()

        binding.ivlikelist.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, LikeListActivity::class.java)
            startActivity(intent)
        })

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

                        if(preferences.getInt("Favorite" + response.login, 0) == 1){
                            binding.ivstar.setImageResource(R.drawable.ic_yellow_star)
                        }else if(preferences.getInt("Favorite" + response.login, 0) == 0){
                            binding.ivstar.setImageResource(R.drawable.ic_white_star)
                        }

                        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

                        binding.ivstar.setOnClickListener(View.OnClickListener {
                            if(preferences.getInt("Favorite" + response.login, 0) == 1){
                                i--
                                binding.ivstar.setImageResource(R.drawable.ic_white_star)
                                editor.putInt("Favorite" + response.login, 0).commit()
                            }else if(preferences.getInt("Favorite" + response.login, 0) == 0){
                                i++
                                binding.ivstar.setImageResource(R.drawable.ic_yellow_star)
                                editor.putInt("Favorite" + response.login, 1).commit()
                                editor.putString("Url" + i, response.avatar_url).commit()
                                editor.putString("Name" + i, response.login).commit()
                            }
                        })

                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                }

            })
        })
    }
}