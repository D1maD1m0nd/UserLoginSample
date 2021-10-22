package com.example.userloginsample.ui.githubusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userloginsample.databinding.FragmentGitHubBinding
import com.example.userloginsample.ui.App.Companion.userRepository
import com.example.userloginsample.ui.githubusers.adapter.DiffUtilsUser
import com.example.userloginsample.ui.githubusers.adapter.GitHubUsersAdapter


class GitHubFragment : Fragment() {
    private var _binding: FragmentGitHubBinding? = null
    private val binding: FragmentGitHubBinding
        get() = _binding!!
    private val adapter = GitHubUsersAdapter()
    private val repo = userRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGitHubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listUserRcView.layoutManager = LinearLayoutManager(context)
        binding.listUserRcView.adapter = adapter
        binding.listUserRcView.setHasFixedSize(true)
        val newList = userRepository.getUsers()
        val oldList = adapter.list
        val utils = DiffUtilsUser(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(utils)
        diffResult.dispatchUpdatesTo(adapter)
    }

    companion object {
        @JvmStatic
        fun newInstance() = GitHubFragment()
    }
}