package com.example.userloginsample.ui

import android.app.Application
import com.example.userloginsample.impl.UserRepositoryImpl

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        userRepository = UserRepositoryImpl()
    }

    companion object {
        lateinit var userRepository: UserRepositoryImpl
    }
}