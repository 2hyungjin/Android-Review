package com.example.sampleapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapp.R
import com.example.sampleapp.databinding.MainFragmentBinding
import com.example.sampleapp.ui.adapter.UserListAdapter
import com.example.sampleapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val userViewModel: UserViewModel by viewModels()
    lateinit var binding: MainFragmentBinding
    lateinit var listAdapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewCreated(view, savedInstanceState)
        init()
        observe()
    }

    private fun init() {
        listAdapter = UserListAdapter()
        binding.rvUsersMainFragment.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        userViewModel.getUsers()
    }

    private fun observe() {
        userViewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
        })

        userViewModel.users.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }

}