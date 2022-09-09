package com.example.sadlfj.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.bumptech.glide.Glide
import com.example.sadlfj.contract.Contract
import com.example.sadlfj.presenter.Presenter
import com.example.sadlfj.model.UserResponse
import com.example.sadlfj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Contract.View {
    lateinit var presenter: Presenter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = Presenter(this)

        binding.constraint.setOnClickListener{
            hideKey()
        }

        binding.ivSearch.setOnClickListener {
            val id = binding.etuserId.text.toString()
            if(id != null){
                presenter.getUser(id)
                hideKey()
            }
        }
    }

    override fun showUser(userResponse: UserResponse) {
        binding.ivwhite.setImageResource(0)

        Glide.with(this)
            .load(userResponse.avatar_url)
            .into(binding.ivprofile)

        binding.tvid.text = userResponse.login

        binding.tvname.text = userResponse.name

        binding.tvfollowers.text = Integer.parseInt(userResponse.followers.toString()).toString()

        binding.tvfollowing.text = Integer.parseInt(userResponse.following.toString()).toString()

        binding.tvdes.text = userResponse.bio

        binding.tvemail.text = userResponse.email

        binding.tvcompany.text = userResponse.company

        var input = userResponse.created_at
        var token = input.split('-')
        var token1 = token[2].split('T')
        binding.tvjoin.setText(token[0] + "-" + token[1] + "-" + token1[0])

        binding.tvrepo.text = Integer.parseInt(userResponse.public_repos.toString()).toString()

        binding.btUrl.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(userResponse.html_url))
            startActivity(intent)
        }
    }

    fun hideKey(){
        val inputMethodManager : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}