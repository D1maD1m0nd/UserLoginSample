package com.example.userloginsample.utils

import com.example.userloginsample.domain.User
import com.example.userloginsample.ui.event_bus.EventBusFragment
import com.example.userloginsample.ui.githubusers.GitHubFragment
import com.example.userloginsample.ui.login.LoginFragment
import com.example.userloginsample.ui.profile.ProfileFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun login() = FragmentScreen { LoginFragment.newInstance() }
    fun gitHubUsers() = FragmentScreen { GitHubFragment.newInstance() }
    fun profile(user: User) = FragmentScreen("Profile_$user") { ProfileFragment.newInstance(user) }
    fun eventBus() = FragmentScreen { EventBusFragment.newInstance() }
}