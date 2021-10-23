package com.example.userloginsample.ui.githubusers.contract

import com.example.userloginsample.domain.User

class Contract {
    interface MainView {
        fun init()
        fun updateList(users: List<User>)
    }

    interface IUserListPresenter {
        fun onAttach(v: MainView)
        fun openUserScreen(login: String)
    }
}