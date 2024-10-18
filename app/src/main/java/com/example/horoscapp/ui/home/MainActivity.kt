package com.example.horoscapp.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Set up view binding
    private lateinit var binding: ActivityMainBinding

    // This is the navigation controller that will be used to navigate between fragments
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Inflate the binding and set it as the root for the UI
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // This function will initialize the UI and set up the navigation
    private fun initUI() {
        initNavigation()
    }

    // This function will connect each Fragment to the navigation graph, and set up the navigation between them
    private fun initNavigation() {
        // Find the NavHostFragment, which is the container for the fragments, and set it as the navigation controller (The fragmentContainerView is a NavHostFragment, so we have to cast it that way)
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        // Set the navigation controller to the navigation graph
        navController = navHost.navController

        // Set up the bottom navigation view to use the navigation controller
        binding.bottomNavView.setupWithNavController(navController)
    }
}