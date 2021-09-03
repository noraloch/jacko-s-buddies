package com.example.jackosbuddies.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.jackosbuddies.R
import com.example.jackosbuddies.databinding.FragmentBrowseBinding

/**
 * A simple [Fragment] subclass.
 */
class BrowseFragment : Fragment(R.layout.fragment_browse) {

    private lateinit var binding: FragmentBrowseBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBrowseBinding.bind(view)
    }
}