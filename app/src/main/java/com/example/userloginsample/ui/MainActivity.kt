package com.example.userloginsample.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.core.view.isVisible
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
        if (savedInstanceState == null) {
            presenter.onAttach(this)
        } else {

            presenter = lastCustomNonConfigurationInstance as EditAuthPresenter
        }

        init()
    }

    private fun init() {
        initPresenter()
        initView()
    }

    private fun initPresenter() {
        if (lastNonConfigurationInstance is EditAuthPresenter) {
            presenter = lastCustomNonConfigurationInstance as EditAuthPresenter
        } else {
            presenter.onAttach(this)
        }
    }

    private fun initView() = with(binding) {
        initPasswordChangeListener()
        initLoginChangeListener()
        setOnLoginButtonClickListener()
    }

    private fun setOnLoginButtonClickListener() = with(binding) {
        loginButton.setOnClickListener {
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()
            presenter.onLogin(login, password)
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

    override fun setState(state: AuthState) = with(binding) {
        when (state) {
            AuthState.IDLE -> contentLayout.isVisible = true
            AuthState.ERROR -> this@MainActivity.onStateError()
            AuthState.SUCCESS -> this@MainActivity.onStateSuccess()
            else -> null
        }
    }

    private fun onStateError() = with(binding) {
        contentLayout.isVisible = false
        progressBar.isVisible = true
        Handler(Looper.getMainLooper()).postDelayed(3000) {
            contentLayout.isVisible = true
            progressBar.isVisible = false
            Toast.makeText(
                this@MainActivity,
                getString(R.string.userNotFound),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun onStateSuccess() = with(binding) {
        contentLayout.isVisible = false
        progressBar.isVisible = true
        Handler(Looper.getMainLooper()).postDelayed(3000) {
            contentLayout.isVisible = false
            progressBar.isVisible = false
            Toast.makeText(
                this@MainActivity,
                getString(R.string.AuthSuccessMessage),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter
    }

    override fun setPasswordError(code: PasswordState) = with(binding) {
        passwordEditText.error = getString(R.string.IncorectPasswordError)
    }

    override fun setLoginError(code: LoginState) = with(binding) {
        loginEditText.error = getString(R.string.LoginIncorrectError)
    }


}