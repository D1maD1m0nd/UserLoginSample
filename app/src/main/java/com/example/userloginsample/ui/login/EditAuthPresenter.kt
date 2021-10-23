package com.example.userloginsample.ui.login

import com.example.userloginsample.constants.*
import com.example.userloginsample.domain.User
import com.example.userloginsample.impl.UserRepository
import com.example.userloginsample.ui.App
import com.github.terrakok.cicerone.Router

class EditAuthPresenter(private val router: Router) : Contract.Presenter() {
    private var repo: UserRepository = App.userRepository
    private var state: AuthState = AuthState.IDLE

    override fun onFirstViewAttach() {
        viewState.setState(AuthState.IDLE)
        super.onFirstViewAttach()
    }

    override fun onOpenGitHubUserScreen() {
        router.navigateTo(Screens.gitHubUsers())
    }

    override fun onChangeLogin(login: String) {
        val state = validateLogin(login)
        if (state != LoginState.SUCCESS) {
            viewState.setLoginError(state)
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
            viewState.setPasswordError(state)
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
            viewState.setLoginError(validateLoginResult)
        }
        if (validatePasswordResult != PasswordState.SUCCESS) {
            viewState.setPasswordError(validatePasswordResult)
        }
        return validateLoginResult != LoginState.INCORRECT_LOGIN && validatePasswordResult != PasswordState.INCORRECT_PASSWORD
    }

    private fun isNullUser(user: User?) {
        val state = if (user == null) {
            AuthState.ERROR
        } else {
            AuthState.SUCCESS
        }
        viewState.setState(state)
        this.state = state
    }
}