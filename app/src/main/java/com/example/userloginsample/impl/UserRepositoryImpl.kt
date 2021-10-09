package com.example.userloginsample.impl

import com.example.userloginsample.domain.User

class UserRepositoryImpl : UserRepository {
    override fun getUserData(login: String, password: String): User? {
        return findUser(login, password)
    }

    private fun findUser(login: String, password: String): User? {
        return getUsers().findLast { it.login == login && it.password == password }
    }

    private fun getUsers() = listOf(
        User("Dima123", "31dsa!#"),
        User("Kostya123", "31221d!!2sssss"),
        User("Qwerty", "qwerty123!"),
        User("Mir132", "LOfff"),
        User("admin", "admin"),
        User("root", "root")
    )
}