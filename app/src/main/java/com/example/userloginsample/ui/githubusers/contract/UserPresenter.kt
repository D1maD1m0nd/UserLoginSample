package com.example.userloginsample.ui.githubusers.contract

import com.example.userloginsample.domain.User
import com.example.userloginsample.impl.UserRepositoryImpl
import com.example.userloginsample.utils.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class UserPresenter(private val router: Router) : Contract.IUserListPresenter() {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val repo = UserRepositoryImpl()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        initSubscriber()
    }

    private fun initSubscriber() {
        compositeDisposable.add(
            repo.users
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    updateUsers(it)
                }
        )
    }

    private fun updateUsers(list: ArrayList<User>) {
        viewState.updateList(list)
    }

    override fun openUserScreen(login: String) {
        val user = repo.findUserByLogin(login)
        user?.let {
            router.navigateTo(Screens.profile(it))
        }
    }
}