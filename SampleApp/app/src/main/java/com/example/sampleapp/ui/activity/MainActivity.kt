package com.example.sampleapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        super.setContentView(binding.root)
        initNavigation()
    }

    private fun initNavigation() {
        navController = findNavController(R.id.fragmentContainerView)
//        val appBarConfig = AppBarConfiguration(
//            topLevelDestinationIds = setOf(R.id.mainFragment),
//        )
//        binding.toolbar.setupWithNavController(navController, appBarConfig)
//
//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            if (destination.id == R.id.mainFragment) {
//                binding.toolbar.visibility = View.GONE
//            }
//        }
    }

}