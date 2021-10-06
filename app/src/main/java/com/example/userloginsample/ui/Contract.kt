package com.example.userloginsample.ui

import com.example.userloginsample.constants.AuthState
import com.example.userloginsample.constants.LoginState
import com.example.userloginsample.constants.PasswordState

class Contract {
    interface View {
        fun setState(state: AuthState)
        fun setPasswordError(code: PasswordState)
        fun setLoginError(code: LoginState)

    }

    interface Presenter {
        fun onAttach(view: View)
        fun onDetach()
        fun onChangeLogin(login: String)
        fun onChangePassword(password: String)
        fun onLogin()
    }
}