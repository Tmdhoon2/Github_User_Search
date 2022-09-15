package com.example.sadlfj.viewmodel

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.sadlfj.api.ApiProvider
import com.example.sadlfj.model.UserRepository
import com.example.sadlfj.model.UserResponse
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val userRepository = UserRepository(this)

    private val _userResponse: MutableLiveData<UserResponse> = MutableLiveData()
    val userResponse: LiveData<UserResponse> = _userResponse
    private val _userProfile: MutableLiveData<Int> = MutableLiveData()
    val userProfile: LiveData<Int> = _userProfile

    fun getUser(id: String) {
        userRepository.getUser(id)
    }

    fun setUser(userResponse: UserResponse) {
        _userResponse.value = userResponse
    }

    fun setProfile(){
        _userProfile.value = 0
    }

    fun url(){

    }

}