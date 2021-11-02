package com.example.sampleapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import com.example.sampleapp.databinding.UserRvItemBinding
import com.example.sampleapp.model.entity.User
import com.example.sampleapp.ui.adapter.viewholder.BaseViewHolder

class UserListAdapter(val clickListener: OnItemClickListener) :
    ListAdapter<User, UserListAdapter.ViewHolder>(UserDiffUtil()) {

    inner class ViewHolder(private val binding: UserRvItemBinding) :
        BaseViewHolder<User>(binding.root) {
        override fun bind(item: User) {
            binding.root.setOnClickListener {
                clickListener.onClick(item)
            }
            binding.user = item
        }
    }

    interface OnItemClickListener {
        fun onClick(user: User)
        fun onLongClick(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UserDiffUtil : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
