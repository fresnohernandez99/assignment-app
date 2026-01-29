package com.fresnohernandez.assignmentapp.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.fresnohernandez.assignmentapp.R
import com.fresnohernandez.assignmentapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupBottomNavigation() {
        with(binding) {
            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.homeFragment -> {
                        true
                    }

                    R.id.favoritesFragment -> {
                        //Toast.makeText(this, getString(R.string.favorites), Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.profileFragment -> {
                        //Toast.makeText(this, getString(R.string.account), Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false
                }
            }

            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.mainContainerView) as NavHostFragment
            val navController = navHostFragment.navController
            bottomNavigation.setupWithNavController(navController)
        }
    }
}