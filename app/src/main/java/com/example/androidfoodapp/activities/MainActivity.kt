package com.example.androidfoodapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.androidfoodapp.R
import com.example.androidfoodapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.btn_nav)
        val navcontroller = Navigation.findNavController(this, R.id.fragmentContainerView)
        NavigationUI.setupWithNavController(bottomNavigation,navcontroller)
    }
}