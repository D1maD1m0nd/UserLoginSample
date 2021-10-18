package com.example.userloginsample.ui

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
import androidx.fragment.app.Fragment
import com.example.userloginsample.R
import com.example.userloginsample.constants.AuthState
import com.example.userloginsample.constants.LoginState
import com.example.userloginsample.constants.PasswordState
import com.example.userloginsample.databinding.FragmentLoginBinding


class LoginFragment : Fragment(), Contract.View {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    private var presenter: Contract.Presenter = EditAuthPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retain this fragment across configuration changes.
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
        initPresenter()
        initView()
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

    private fun initPresenter() {
        presenter.onAttach(this)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun setState(state: AuthState) = with(binding) {
        when (state) {

            AuthState.IDLE -> contentLayout.isVisible = true
            AuthState.ERROR -> this@LoginFragment.onStateError()
            AuthState.SUCCESS -> this@LoginFragment.onStateSuccess()
            else -> null
        }
    }

    override fun setPasswordError(code: PasswordState) = with(binding) {
        passwordEditText.error = getString(R.string.IncorectPasswordError)
    }

    override fun setLoginError(code: LoginState) = with(binding) {
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
        }
    }


    companion object {
        fun newInstance() = LoginFragment()
        const val DELAY_TIME = 3000L
    }
}