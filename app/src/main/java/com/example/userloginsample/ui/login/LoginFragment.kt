package com.example.userloginsample.ui.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.core.view.isVisible
import com.example.userloginsample.R
import com.example.userloginsample.databinding.FragmentLoginBinding
import com.example.userloginsample.ui.App
import com.example.userloginsample.utils.Screens
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class LoginFragment : MvpAppCompatFragment(), Contract.View {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    private val presenter by moxyPresenter {
        EditAuthPresenter(App.INSTANCE.router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
        initPasswordChangeListener()
        initLoginChangeListener()
        setOnLoginButtonClickListener()
        setOnEventBusButtonClickListener()
    }

    private fun setOnEventBusButtonClickListener() {
        binding.eventBusButton.setOnClickListener {
            App.INSTANCE.router.navigateTo(Screens.eventBus())
        }
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun setState(state: Contract.AuthState): Unit = with(binding) {
        when (state) {
            Contract.AuthState.IDLE -> contentLayout.isVisible = true
            Contract.AuthState.ERROR -> this@LoginFragment.onStateError()
            Contract.AuthState.SUCCESS -> this@LoginFragment.onStateSuccess()
            else -> Unit
        }
    }

    override fun setPasswordError(code: Contract.PasswordState) = with(binding) {
        passwordEditText.error = getString(R.string.IncorectPasswordError)
    }

    override fun setLoginError(code: Contract.LoginState) = with(binding) {
        loginEditText.error = getString(R.string.LoginIncorrectError)
    }


    private fun onStateError() = with(binding) {
        contentLayout.isVisible = false
        progressBar.isVisible = true
        Handler(Looper.getMainLooper()).postDelayed(DELAY_TIME) {
            Toast.makeText(
                context,
                getString(R.string.userNotFound),
                Toast.LENGTH_SHORT
            ).show()
            contentLayout.isVisible = true
            progressBar.isVisible = false
        }
    }

    private fun onStateSuccess() = with(binding) {
        contentLayout.isVisible = false
        progressBar.isVisible = true
        Handler(Looper.getMainLooper()).postDelayed(DELAY_TIME) {
            Toast.makeText(
                context,
                getString(R.string.AuthSuccessMessage),
                Toast.LENGTH_SHORT
            ).show()
            contentLayout.isVisible = false
            progressBar.isVisible = false
            presenter.onOpenGitHubUserScreen()
        }
    }


    companion object {
        fun newInstance() = LoginFragment()
        const val DELAY_TIME = 3000L
    }
}