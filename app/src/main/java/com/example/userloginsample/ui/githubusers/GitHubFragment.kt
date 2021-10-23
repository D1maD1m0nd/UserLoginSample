package com.example.userloginsample.ui.githubusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userloginsample.databinding.FragmentGitHubBinding
import com.example.userloginsample.domain.User
import com.example.userloginsample.ui.App
import com.example.userloginsample.ui.App.Companion.userRepository
import com.example.userloginsample.ui.githubusers.adapter.DiffUtilsUser
import com.example.userloginsample.ui.githubusers.adapter.GitHubUsersAdapter
import com.example.userloginsample.ui.githubusers.contract.Contract
import com.example.userloginsample.ui.githubusers.contract.OnItemViewClickListener
import com.example.userloginsample.ui.githubusers.contract.UserPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class GitHubFragment : MvpAppCompatFragment(), Contract.UsersView {
    private var _binding: FragmentGitHubBinding? = null
    private val binding: FragmentGitHubBinding
        get() = _binding!!
    private val onListItemClickListener = object : OnItemViewClickListener {
        override fun onItemViewClick(login: String) {
            presenter.openUserScreen(login)
        }
    }
    private val adapter = GitHubUsersAdapter(onListItemClickListener)
    private val presenter by moxyPresenter {
        UserPresenter(App.INSTANCE.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGitHubBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initRcView() = with(binding) {
        listUserRcView.layoutManager = LinearLayoutManager(context)
        listUserRcView.adapter = adapter
        listUserRcView.setHasFixedSize(true)
    }

    private fun diff() {
        val newList = userRepository.getUsers()
        val oldList = adapter.list
        val utils = DiffUtilsUser(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(utils)
        diffResult.dispatchUpdatesTo(adapter)
    }

    override fun init() {
        initRcView()
    }

    override fun updateList(users: List<User>) {
        adapter.setData(users)
        diff()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = GitHubFragment()
    }
}