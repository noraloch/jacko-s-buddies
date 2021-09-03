package com.example.jackosbuddies.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.jackosbuddies.R
import com.example.jackosbuddies.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
    }

}