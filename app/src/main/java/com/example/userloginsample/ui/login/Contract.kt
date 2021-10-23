package com.example.userloginsample.ui.login

import com.example.userloginsample.constants.AuthState
import com.example.userloginsample.constants.LoginState
import com.example.userloginsample.constants.PasswordState
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

class Contract {
    interface View : MvpView {
        @AddToEndSingle
        fun setState(state: AuthState)

        @AddToEndSingle
        fun setPasswordError(code: PasswordState)

        @AddToEndSingle
        fun setLoginError(code: LoginState)

    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun onOpenGitHubUserScreen()
        abstract fun onChangeLogin(login: String)
        abstract fun onChangePassword(password: String)
        abstract fun onLogin(login: String, password: String)
    }
}