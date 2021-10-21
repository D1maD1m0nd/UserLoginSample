package com.example.userloginsample.constants

import com.example.userloginsample.ui.githubusers.GitHubFragment
import com.example.userloginsample.ui.login.LoginFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun login() = FragmentScreen { LoginFragment.newInstance() }
    fun gitHubUsers() = FragmentScreen { GitHubFragment.newInstance() }
//    fun profile(userId: Long) = FragmentScreen("Profile_$userId") { ProfileFragment(userId) }
//    fun repositories(url: String) = FragmentScreen { AddressSearchFragment() }
}