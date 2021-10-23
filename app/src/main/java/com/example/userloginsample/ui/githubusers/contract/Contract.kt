package com.example.userloginsample.ui.githubusers.contract

import com.example.userloginsample.domain.User
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

class Contract {
    interface UsersView : MvpView {
        @AddToEndSingle
        fun init()

        @AddToEndSingle
        fun updateList(users: List<User>)
    }

    abstract class IUserListPresenter : MvpPresenter<UsersView>() {
        abstract fun openUserScreen(login: String)
    }
}