package com.example.userloginsample.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.userloginsample.constants.AuthState
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
        loginEditText.setOnFocusChangeListener { v, hasFocus: Boolean ->
            if (!hasFocus) {
                val editTextView = v as EditText
                presenter.onChangeLogin(editTextView.text.toString())
                Toast.makeText(this@MainActivity, "HAS Focus", Toast.LENGTH_SHORT).show()
            }
        }
        passwordEditText.setOnFocusChangeListener { v, hasFocus: Boolean ->
            if (!hasFocus) {
                val editTextView = v as EditText
                presenter.onChangePassword(editTextView.text.toString())
                Toast.makeText(this@MainActivity, "HAS Focus", Toast.LENGTH_SHORT).show()
            }
        }
        loginButton.setOnClickListener {
        }
    }

    override fun setState(state: AuthState) {
        TODO("Not yet implemented")
    }

    override fun setPasswordError(code: Int) {
        TODO("Not yet implemented")
    }

    override fun setLoginError(code: Int) {
        TODO("Not yet implemented")
    }
}