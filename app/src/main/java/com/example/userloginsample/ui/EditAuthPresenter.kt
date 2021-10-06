package com.example.userloginsample.ui

import com.example.userloginsample.constants.LoginState
import com.example.userloginsample.constants.PasswordState
import com.example.userloginsample.constants.RegexValidateConstants

class EditAuthPresenter : Contract.Presenter {
    private var view: Contract.View? = null

    override fun onAttach(view: Contract.View) {
        this.view = view
    }

    override fun onDetach() {
        TODO("Not yet implemented")
    }

    override fun onChangeLogin(login: String) {
        val state = validateLogin(login)
        if (state != LoginState.SUCCESS) {
            view?.setLoginError(state)
        }
    }

    private fun validateLogin(login: String): LoginState {
        val isCorrect = Regex(RegexValidateConstants.CORRECT_LOGIN.value).matches(login)
        return when {
            isCorrect -> LoginState.SUCCESS
            else -> LoginState.INCORRECT_LOGIN
        }
    }


    override fun onChangePassword(password: String) {
        val state = validatePassword(password)
        if (state != PasswordState.SUCCESS) {
            view?.setPasswordError(state)
        }

    }

    private fun validatePassword(password: String): PasswordState {
        val isCorrect = Regex(RegexValidateConstants.CORRECT_PASSWORD.value).matches(password)
        return when {
            isCorrect -> PasswordState.SUCCESS
            else -> PasswordState.INCORRECT_PASSWORD
        }
    }

    override fun onLogin() {
        TODO("Not yet implemented")
    }
}