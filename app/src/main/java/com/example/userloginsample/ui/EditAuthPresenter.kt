package com.example.userloginsample.ui

import com.example.userloginsample.constants.LoginState
import com.example.userloginsample.constants.PasswordState

class EditAuthPresenter : Contract.Presenter {
    override fun onAttach(view: Contract.View) {
        TODO("Not yet implemented")
    }

    override fun onDetach() {
        TODO("Not yet implemented")
    }

    override fun onChangeLogin(login: String) {
        validateLogin(login)
    }

    private fun validateLogin(login: String): LoginState {
        if (login.isEmpty()) {
            return LoginState.EMPTY_LOGIN_ERROR
        }
        return LoginState.SUCCESS
    }


    override fun onChangePassword(password: String) {
        validatePassword(password)
    }

    private fun validatePassword(password: String): PasswordState {
        return PasswordState.SUCCESS
    }

    override fun onLogin() {
        TODO("Not yet implemented")
    }
}