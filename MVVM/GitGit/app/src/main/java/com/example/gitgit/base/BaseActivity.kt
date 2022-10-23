package com.example.gitgit.base

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.dmsport_android.initPref

abstract class BaseActivity<V : ViewDataBinding>(
    @LayoutRes private val layoutId : Int
) : AppCompatActivity() {

    protected val binding : V by lazy {
        DataBindingUtil.setContentView(this, layoutId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }

}