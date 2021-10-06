package com.example.userloginsample.ui

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.userloginsample.R
import com.example.userloginsample.constants.AuthState
import com.example.userloginsample.constants.LoginState
import com.example.userloginsample.constants.PasswordState
import com.example.userloginsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Contract.View {
    private lateinit var binding: ActivityMainBinding
    private var presenter: Contract.Presenter = EditAuthPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        presenter.onAttach(this)
        initView()
    }

    private fun initView() = with(binding) {
        initPasswordChangeListener()
        initLoginChangeListener()
        setOnLoginButtonClickListener()
    }

    private fun setOnLoginButtonClickListener() = with(binding) {
        loginButton.setOnClickListener {
        }
    }

    private fun initPasswordChangeListener() = with(binding) {
        passwordEditText.setOnFocusChangeListener { v, hasFocus: Boolean ->
            if (!hasFocus) {
                presenter.onChangePassword((v as EditText).text.toString())
            }
        }
    }

    private fun initLoginChangeListener() = with(binding) {
        loginEditText.setOnFocusChangeListener { v, hasFocus: Boolean ->
            if (!hasFocus) {
                presenter.onChangeLogin((v as EditText).text.toString())
            }
        }
    }

    override fun setState(state: AuthState) {
        TODO("Not yet implemented")
    }

    override fun setPasswordError(code: PasswordState) = with(binding) {
        passwordEditText.error = getString(R.string.IncorectPasswordError)
    }

    override fun setLoginError(code: LoginState) = with(binding) {
        loginEditText.error = getString(R.string.LoginIncorrectError)
    }
}