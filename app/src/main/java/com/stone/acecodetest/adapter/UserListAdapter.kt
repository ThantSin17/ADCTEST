package com.stone.acecodetest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stone.acecodetest.databinding.UserItemLayoutBinding
import com.stone.acecodetest.listener.OnClickListener
import com.stone.acecodetest.model.User

class UserListAdapter(private val listener: OnClickListener):ListAdapter<User,UserListAdapter.UserListViewHolder>(
    object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id==newItem.id
        }
    }
) {
    class UserListViewHolder(private val binding : UserItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User,listener: OnClickListener){
            binding.user=user
            itemView.setOnClickListener {
                listener.onItemClickListener(user)
            }
        }

        companion object {
            fun create(parent: ViewGroup) :UserListViewHolder{
                val binding = UserItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return UserListViewHolder(binding)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {

       return UserListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val current=currentList[position]
        holder.bind(current, listener)
    }
}