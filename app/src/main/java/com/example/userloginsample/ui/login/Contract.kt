package com.example.userloginsample.ui.login

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

class Contract {
    enum class PasswordState {
        SUCCESS, INCORRECT_PASSWORD
    }

    enum class LoginState {
        SUCCESS, INCORRECT_LOGIN
    }

    enum class AuthState {
        ERROR, IDLE, SUCCESS, LOADING
    }

    enum class RegexValidateConstants(val value: String) {
        CORRECT_PASSWORD("^[\\S+]{5,40}"), CORRECT_LOGIN("^\\D[\\S+$]{3,40}")
    }

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