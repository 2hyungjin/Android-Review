package com.example.sampleapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.sampleapp.R
import com.example.sampleapp.databinding.MainActivityBinding
import com.example.sampleapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var navController: NavController
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        super.setContentView(binding.root)

        initNavigation()

        binding.svMainActivity.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank()) userViewModel.getUsers()
                else userViewModel.getUser(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        binding.appCompatButton.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_myUserFragment)
        }

    }

    private fun initNavigation() {
        navController = findNavController(R.id.fragmentContainerView)
        val appBarConfig = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.mainFragment),
        )
        binding.toolbar.setupWithNavController(navController, appBarConfig)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.myUserFragment) {
                binding.svMainActivity.visibility = View.GONE
            } else {
                binding.svMainActivity.visibility = View.VISIBLE
            }
        }
    }

}