package com.example.userloginsample.impl

import com.example.userloginsample.domain.User

interface UserRepository {
    fun getUserData(login: String, password: String): User?
}