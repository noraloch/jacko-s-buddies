package com.example.jackosbuddies.view

import android.os.Bundle
import androidx.core.view.isVisible
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jackosbuddies.R
import com.example.jackosbuddies.databinding.FragmentBrowseBinding
import com.example.jackosbuddies.model.Kat
import com.example.jackosbuddies.util.ApiState
import com.example.jackosbuddies.view.adapter.KatAdapter
import com.example.jackosbuddies.viewmodel.KatViewModel

/**
 * A simple [Fragment] subclass.
 */
class BrowseFragment : Fragment(R.layout.fragment_browse) {

    private lateinit var binding: FragmentBrowseBinding

    private val katViewModel by activityViewModels<KatViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBrowseBinding.bind(view)
        initViews()
        setupObservers()
        katViewModel.fetchKatList(10)
    }

    // with(receiver) is 1 of 5 scope functions
    private fun initViews() = with(binding) {
        rvKats.adapter = KatAdapter()
    }

    private fun setupObservers() = with(katViewModel) {
        katState.observe(viewLifecycleOwner) { state ->
            binding.pbLoading.isVisible = state is ApiState.Loading
            if (state is ApiState.Success) handleSuccess(state.data)
            if (state is ApiState.Failure) handleFailure(state.errorMsg)
        }
    }

    private fun handleSuccess(kats: List<Kat>) {
        Log.d(TAG, "ApiState.Success: $kats")
        (binding.rvKats.adapter as KatAdapter).updateList(kats)
    }

    private fun handleFailure(errorMsg: String) {
        Log.d(TAG, "ApiState.Failure: $errorMsg")
    }

    companion object {
        private const val TAG = "BrowseFragment"
    }
}