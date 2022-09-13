package com.example.sadlfj.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.sadlfj.api.ApiProvider
import com.example.sadlfj.model.UserResponse
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){

    private val _userResponse : MutableLiveData<UserResponse> = MutableLiveData()
    private val _image : MutableLiveData<Int> = MutableLiveData()
    val userResponse : LiveData<UserResponse>
    get() = _userResponse
    val image : LiveData<Int>
    get() = _image

    @BindingAdapter("avatar_url")
    fun loadImage(view : CircleImageView, url : String?){
        Glide.with(view.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

    fun getUser(id : String){
        ApiProvider.retrofit.getUserId(id).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    _userResponse.value = response.body()!!
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

            }
        })
    }

}