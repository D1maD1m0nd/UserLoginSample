package com.example.userloginsample.ui

import android.app.Application
import com.example.userloginsample.domain.EventBus
import com.example.userloginsample.impl.UserRepositoryImpl
import com.github.terrakok.cicerone.Cicerone

class App : Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val eventBus = EventBus()

    override fun onCreate() {
        super.onCreate()
        userRepository = UserRepositoryImpl()
        INSTANCE = this
    }

    companion object {
        lateinit var userRepository: UserRepositoryImpl
        internal lateinit var INSTANCE: App
            private set
    }
}