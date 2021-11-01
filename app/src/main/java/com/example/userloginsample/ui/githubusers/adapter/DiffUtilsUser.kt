package com.example.userloginsample.ui.githubusers.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.userloginsample.domain.User

class DiffUtilsUser(private val oldList: List<User>, private val newList: List<User>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].login == newList[newItemPosition].login
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].login == newList[newItemPosition].login
    }
}