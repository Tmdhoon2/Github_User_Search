package com.example.sadlfj.presenter

import com.example.sadlfj.contract.Contract
import com.example.sadlfj.model.UserRepository
import com.example.sadlfj.model.UserResponse
import com.example.sadlfj.view.MainActivity

class Presenter(val view: MainActivity) : Contract.Presenter {
    lateinit var userRepository: UserRepository

    override fun getUser(id: String) {
        userRepository = UserRepository(this)
        userRepository.getUser(id)
    }

    override fun setUser(userResponse: UserResponse) {
        view.showUser(userResponse)
    }
}