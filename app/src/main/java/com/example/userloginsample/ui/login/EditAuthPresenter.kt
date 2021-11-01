package com.example.userloginsample.ui.login

import com.example.userloginsample.domain.User
import com.example.userloginsample.impl.UserRepository
import com.example.userloginsample.ui.App
import com.example.userloginsample.utils.Screens
import com.github.terrakok.cicerone.Router

class EditAuthPresenter(private val router: Router) : Contract.Presenter() {
    private var repo: UserRepository = App.userRepository
    private var state: Contract.AuthState = Contract.AuthState.IDLE

    override fun onFirstViewAttach() {
        viewState.setState(Contract.AuthState.IDLE)
        super.onFirstViewAttach()
    }

    override fun onOpenGitHubUserScreen() {
        router.navigateTo(Screens.gitHubUsers())
    }

    override fun onChangeLogin(login: String) {
        val state = validateLogin(login)
        if (state != Contract.LoginState.SUCCESS) {
            viewState.setLoginError(state)
        }
    }

    private fun validateLogin(login: String): Contract.LoginState {
        val isCorrect = Regex(Contract.RegexValidateConstants.CORRECT_LOGIN.value).matches(login)
        return when {
            isCorrect -> Contract.LoginState.SUCCESS
            else -> Contract.LoginState.INCORRECT_LOGIN
        }
    }


    override fun onChangePassword(password: String) {
        val state = validatePassword(password)
        if (state != Contract.PasswordState.SUCCESS) {
            viewState.setPasswordError(state)
        }

    }

    private fun validatePassword(password: String): Contract.PasswordState {
        val isCorrect =
            Regex(Contract.RegexValidateConstants.CORRECT_PASSWORD.value).matches(password)
        return when {
            isCorrect -> Contract.PasswordState.SUCCESS
            else -> Contract.PasswordState.INCORRECT_PASSWORD
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
        if (validateLoginResult != Contract.LoginState.SUCCESS) {
            viewState.setLoginError(validateLoginResult)
        }
        if (validatePasswordResult != Contract.PasswordState.SUCCESS) {
            viewState.setPasswordError(validatePasswordResult)
        }
        return validateLoginResult != Contract.LoginState.INCORRECT_LOGIN && validatePasswordResult != Contract.PasswordState.INCORRECT_PASSWORD
    }

    private fun isNullUser(user: User?) {
        val state = if (user == null) {
            Contract.AuthState.ERROR
        } else {
            Contract.AuthState.SUCCESS
        }
        viewState.setState(state)
        this.state = state
    }
}