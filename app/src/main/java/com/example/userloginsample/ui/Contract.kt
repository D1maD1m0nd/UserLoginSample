package com.example.userloginsample.ui

import com.example.userloginsample.constants.AuthState

class Contract {
    interface View {
        fun setState(state: AuthState)
        fun setPasswordError(code: Int)
        fun setLoginError(code: Int)

    }

    interface Presenter {
        fun onAttach(view: View)
        fun onDetach()
        fun onChangeLogin(login: String)
        fun onChangePassword(password: String)
        fun onLogin()
    }
}