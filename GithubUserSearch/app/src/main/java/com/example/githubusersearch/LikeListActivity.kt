package com.example.githubusersearch

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusersearch.databinding.ActivityLikeListBinding
import java.util.ArrayList

class LikeListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLikeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLikeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences:SharedPreferences = getSharedPreferences("Favorite", MODE_PRIVATE)
        val editor = preferences.edit()

        binding.recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.setHasFixedSize(true)

        var i:Int = 0


    }
}