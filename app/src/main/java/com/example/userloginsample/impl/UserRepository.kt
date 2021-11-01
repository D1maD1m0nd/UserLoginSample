package com.example.userloginsample.impl

import com.example.userloginsample.domain.User
import io.reactivex.Observable

interface UserRepository {
    fun getUserData(login: String, password: String): User?
    val users: Observable<ArrayList<User>>
}