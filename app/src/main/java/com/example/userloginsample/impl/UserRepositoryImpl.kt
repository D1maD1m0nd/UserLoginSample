package com.example.userloginsample.impl

import com.example.userloginsample.domain.User
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlin.random.Random

class UserRepositoryImpl : UserRepository {
    private val list = ArrayList<User>()
    private var behaviorSubject = BehaviorSubject.createDefault(list)

    init {
        list.add(User("admin", "admin"))
        emulateCall()
    }

    override fun getUserData(login: String, password: String): User? {
        return findUser(login, password)
    }

    override val users: Observable<ArrayList<User>>
        get() = behaviorSubject

    private fun findUser(login: String, password: String): User? {
        return getUsers().findLast { it.login == login && it.password == password }
    }

    fun findUserByLogin(login: String): User? {
        return getUsers().findLast { it.login == login }
    }

    fun getUsers() = list
    private fun emulateCall() {
        addUsers()
    }

    fun addUsers() {
        val count = Random.nextInt(1, 20)
        for (i in 0..count) {
            val user = generateUser()
            list.add(user)
        }
        behaviorSubject.onNext(list)
    }

    private fun generateUser(): User = User("user" + Random.nextInt(), "pass" + Random.nextInt())
}