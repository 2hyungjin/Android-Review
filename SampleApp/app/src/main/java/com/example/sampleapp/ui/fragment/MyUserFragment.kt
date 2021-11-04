package com.example.sampleapp.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapp.R
import com.example.sampleapp.databinding.MyUserFragmentBinding
import com.example.sampleapp.model.entity.DataState
import com.example.sampleapp.model.entity.User
import com.example.sampleapp.ui.adapter.UserListAdapter
import com.example.sampleapp.ui.adapter.core.ItemClickListener
import com.example.sampleapp.viewmodel.MyUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyUserFragment : Fragment(), ItemClickListener<User> {
    private val viewModel: MyUserViewModel by viewModels()

    private lateinit var binding: MyUserFragmentBinding
    private lateinit var userListAdapter: UserListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyUserFragmentBinding.inflate(layoutInflater)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListAdapter = UserListAdapter(this@MyUserFragment)
        binding.rvMyUserFragment.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.getUsers()
        viewModel.userDataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    Log.d("MyUserFragment", "success ${it.data.toString() ?: "null"}")
                    userListAdapter.submitList(it.data)
                }
                is DataState.Failure -> {
                    Toast.makeText(requireContext(), "실패", Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {
                    Log.d("MyUserFragment", "LOADING")
                }
            }
        }


    }

    override fun onClick(item: User) {
        viewModel.removeUser(item)
        val newList = userListAdapter.currentList.toMutableList()
        newList.remove(item)
        userListAdapter.submitList(newList)
    }

    override fun onLongClick(item: User) {
        TODO("Not yet implemented")
    }

}