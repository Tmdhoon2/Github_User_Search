package com.example.gitgit.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gitgit.R
import com.example.gitgit.base.BaseActivity
import com.example.gitgit.databinding.ActivityMainBinding
import com.example.gitgit.view.fragment.FavoriteFragment
import com.example.gitgit.view.fragment.HomeFragment
import com.example.gitgit.view.fragment.SearchFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavigation()
    }

    fun initBottomNavigation(){
        binding.bottomNavigationMain.setOnItemSelectedListener {
            replaceFragment(HomeFragment())
            when(it.itemId){
                R.id.item_home -> replaceFragment(HomeFragment())
                R.id.item_search -> replaceFragment(SearchFragment())
                R.id.item_favorite -> replaceFragment(FavoriteFragment())
            }
            true
        }
    }

    fun replaceFragment(fragment : Fragment): Int {
        return supportFragmentManager.beginTransaction().replace(R.id.frameLayout_main, fragment).commitAllowingStateLoss()
    }
}