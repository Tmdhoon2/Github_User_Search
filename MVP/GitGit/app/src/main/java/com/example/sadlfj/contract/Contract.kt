package com.example.sadlfj.contract

import com.example.sadlfj.model.UserResponse

interface Contract {

    interface View{
        fun showUser(userResponse: UserResponse)
    }

    interface Presenter{
        fun getUser(id: String)
        fun setUser(userResponse: UserResponse)
    }

}