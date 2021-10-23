package com.example.userloginsample.ui.githubusers.contract

import com.example.userloginsample.constants.Screens
import com.example.userloginsample.impl.UserRepositoryImpl
import com.github.terrakok.cicerone.Router

class UserPresenter(private val router: Router) : Contract.IUserListPresenter() {

    private val repo = UserRepositoryImpl()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        updateUsers()
    }

    private fun updateUsers() {
        val list = repo.getUsers()
        viewState.updateList(list)
    }

    override fun openUserScreen(login: String) {
        val user = repo.findUserByLogin(login)
        user?.let {
            router.navigateTo(Screens.profile(it))
        }
    }
}