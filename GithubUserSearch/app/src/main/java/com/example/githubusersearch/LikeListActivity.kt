package com.example.githubusersearch

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusersearch.databinding.ActivityLikeListBinding

class LikeListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLikeListBinding
    private val list = arrayListOf<UserLikes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLikeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences:SharedPreferences = getSharedPreferences("Favorite", MODE_PRIVATE)
        val editor = preferences.edit()

        binding.recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.setHasFixedSize(true)

        binding.recyclerview.adapter = LikeAdapter(list)

        if(intent.hasExtra("url") && intent.hasExtra("id")){
            list.add(UserLikes(intent.getStringExtra("url").orEmpty(), intent.getStringExtra("id").orEmpty()))
        }
    }
}