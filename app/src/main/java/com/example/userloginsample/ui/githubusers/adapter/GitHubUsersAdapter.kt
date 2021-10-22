package com.example.userloginsample.ui.githubusers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userloginsample.R
import com.example.userloginsample.databinding.GitHubUserItemBinding
import com.example.userloginsample.domain.User

class GitHubUsersAdapter : RecyclerView.Adapter<GitHubUsersAdapter.UsersViewHolder>() {
    var list = ArrayList<User>(10)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.git_hub_user_item,
            parent,
            false
        )
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class UsersViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        private val binding = GitHubUserItemBinding.bind(view)
        fun bind(user: User) = with(binding) {
            userLoginTextView.text = user.login
        }
    }
}