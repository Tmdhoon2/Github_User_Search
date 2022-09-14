package com.example.sadlfj.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.sadlfj.R
import com.example.sadlfj.viewmodel.MainViewModel
import androidx.databinding.DataBindingUtil
import com.example.sadlfj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.user = mainViewModel

        binding.ivSearch.setOnClickListener {
            val id = binding.etuserId.text.toString()
            if(id != null){
                Log.d("Test", "MainActivity : onClick")
                mainViewModel.getUser(id)
            }
        }

        binding.lifecycleOwner = this

    }
}