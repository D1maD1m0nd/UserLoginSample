package com.example.userloginsample.ui

import com.example.userloginsample.constants.AuthState
import com.example.userloginsample.constants.LoginState
import com.example.userloginsample.constants.PasswordState
import com.example.userloginsample.constants.RegexValidateConstants
import com.example.userloginsample.domain.User
import com.example.userloginsample.impl.UserRepository

class EditAuthPresenter : Contract.Presenter {
    private lateinit var view: Contract.View
    private var repo: UserRepository = App.userRepository
    private var state: AuthState = AuthState.IDLE
    override fun onAttach(view: Contract.View) {
        this.view = view
        view.setState(state)
    }

    override fun onDetach() {
    }

    override fun onChangeLogin(login: String) {
        val state = validateLogin(login)
        if (state != LoginState.SUCCESS) {
            view.setLoginError(state)
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
            view.setPasswordError(state)
        }

    }

    private fun validatePassword(password: String): PasswordState {
        val isCorrect = Regex(RegexValidateConstants.CORRECT_PASSWORD.value).matches(password)
        return when {
            isCorrect -> PasswordState.SUCCESS
            else -> PasswordState.INCORRECT_PASSWORD
        }
    }

    override fun onLogin(login: String, password: String) {
        val validateAuthResult = validateLoginData(login, password)
        if (validateAuthResult) {
            val user = repo.getUserData(login, password)
            isNullUser(user)
        }
    }

    private fun validateLoginData(login: String, password: String): Boolean {
        val validateLoginResult = validateLogin(login)
        val validatePasswordResult = validatePassword(password)
        if (validateLoginResult != LoginState.SUCCESS) {
            view.setLoginError(validateLoginResult)
        }
        if (validatePasswordResult != PasswordState.SUCCESS) {
            view.setPasswordError(validatePasswordResult)
        }
        return validateLoginResult != LoginState.INCORRECT_LOGIN && validatePasswordResult != PasswordState.INCORRECT_PASSWORD
    }

    private fun isNullUser(user: User?) {
        val state = if (user == null) {
            AuthState.ERROR
        } else {
            AuthState.SUCCESS
        }
        view.setState(state)
        this.state = state
    }
}