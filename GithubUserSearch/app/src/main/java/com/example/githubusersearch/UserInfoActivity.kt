package com.example.githubusersearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.githubusersearch.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("Id")
        val followers = intent.getIntExtra("Followers", 0)
        val following = intent.getIntExtra("Following", 0)
        val url = intent.getStringExtra("Profile")
        val bio = intent.getStringExtra("Bio")

        binding.tvlogin.setText(id.toString())
        binding.tvfollowers.setText(""+followers)
        binding.tvfollowing.setText(""+following)
        binding.tvBio.setText(bio.toString())
        Glide.with(applicationContext)
            .load(url)
            .into(binding.ivprofile)
    }
}