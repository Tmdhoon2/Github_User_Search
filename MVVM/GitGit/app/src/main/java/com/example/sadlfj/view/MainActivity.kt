package com.example.sadlfj.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.example.sadlfj.R
import com.example.sadlfj.viewmodel.MainViewModel
import androidx.databinding.DataBindingUtil
import com.example.sadlfj.databinding.ActivityMainBinding
import java.net.URI

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    companion object{
        lateinit var pref: SharedPreferences
        lateinit var editor : SharedPreferences.Editor
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.user = mainViewModel

        binding.lifecycleOwner = this

        pref = getSharedPreferences("User", MODE_PRIVATE)

        editor = pref.edit()

        binding.ivwhite.setImageResource(R.drawable.img)

        binding.btUrl.setOnClickListener{
            if(pref.getString("url", "") != null) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pref.getString("url", "")))
                startActivity(intent)
            }
        }

        binding.constraint.setOnClickListener{
            hideKey()
        }

        binding.ivSearch.setOnClickListener {
            hideKey()
            val id = binding.etuserId.text.toString()
            if(id != null){
                Log.d("Test", "MainActivity : onClick")
                mainViewModel.getUser(id)
            }
        }
    }

    fun hideKey(){
        val inputMethodManager : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

}