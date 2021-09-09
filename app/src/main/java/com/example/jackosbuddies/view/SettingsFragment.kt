package com.example.jackosbuddies.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jackosbuddies.R
import com.example.jackosbuddies.databinding.FragmentSettingsBinding
import com.example.jackosbuddies.viewmodel.KatViewModel


/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding
    private val katViewModel by activityViewModels<KatViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
        initView()
    }

    private fun initView() = with(binding) {
        sliderLimit.value = katViewModel.limit.toFloat()
        var radioText = ""
        sliderLimit.addOnChangeListener { _, value, _ ->
            toggleApply(katViewModel.limit != value.toInt())
        }
//        val checkedRadioButtonId = radioGroup.checkedRadioButtonId
        // Returns View.NO_ID if nothing is checked.
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            radioText = group.findViewById<View>(checkedId).transitionName

            toggleApply(katViewModel.size != radioText)
        }
        btnApply.setOnClickListener {
            katViewModel.fetchKatList(sliderLimit.value.toInt(), radioText)
        }
    }

    private fun toggleApply(dataChanged: Boolean) {
        binding.btnApply.isVisible = dataChanged
    }
}