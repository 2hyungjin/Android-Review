package com.example.sampleapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.example.sampleapp.databinding.UserRvItemBinding
import com.example.sampleapp.model.entity.User
import com.example.sampleapp.ui.adapter.core.BaseViewHolder
import com.example.sampleapp.ui.adapter.core.ItemClickListener

class UserListAdapter(val clickListener: ItemClickListener<User>) :
    ListAdapter<User, BaseViewHolder<User>>(UserDiffUtil()) {

    inner class ViewHolder(private val binding: UserRvItemBinding) :
        BaseViewHolder<User>(binding.root) {
        override fun bind(item: User) {
            binding.root.setOnClickListener {
                clickListener.onClick(item)
            }
            binding.user = item
            binding.imgUserRvItem.load(item.avatar) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<User> {
        return ViewHolder(
            UserRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<User>, position: Int) {
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
