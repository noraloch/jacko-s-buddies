package com.example.jackosbuddies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jackosbuddies.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}