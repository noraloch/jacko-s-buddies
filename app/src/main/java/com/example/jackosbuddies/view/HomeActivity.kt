package com.example.jackosbuddies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.jackosbuddies.R
import com.example.jackosbuddies.databinding.ActivityHomeBinding
import com.example.jackosbuddies.viewmodel.KatViewModel

class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val katViewModel by viewModels<KatViewModel>()
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
        katViewModel.fetchKatList(10,"full", null, null, false, 0)
    }
}