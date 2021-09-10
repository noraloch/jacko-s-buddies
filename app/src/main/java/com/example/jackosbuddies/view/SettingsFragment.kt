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

    private val boolObject = object {
        var limitChange = false
        var sizeChange = false
        var breedChange = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
        initView()
    }

    private fun initView() = with(binding) {
        sliderLimit.value = katViewModel.limit.toFloat()
        var radioText = ""
        var hasBreeds = katViewModel.hasBreeds


        // listeners
        btnApply.setOnClickListener {

            katViewModel.fetchKatList(sliderLimit.value.toInt(), radioText, hasBreeds)
        }

        sliderLimit.addOnChangeListener { _, value, _ ->
            if (katViewModel.limit != value.toInt()) {
                katViewModel.limit = value.toInt()
                boolObject.limitChange = true
            }
            checkAllToggle()
            boolObject.limitChange = false
        }

        breedSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            hasBreeds = isChecked
            if (katViewModel.hasBreeds != hasBreeds) {
                katViewModel.hasBreeds = isChecked
                boolObject.breedChange = true
            }
            checkAllToggle()
            boolObject.breedChange = false
        }

        // Returns View.NO_ID if nothing is checked.
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            radioText = group.findViewById<View>(checkedId).transitionName
            if (katViewModel.size != radioText) {
                katViewModel.size = radioText
                boolObject.sizeChange = true
            }
            checkAllToggle()
            boolObject.sizeChange = false
        }

    }
    private  fun checkAllToggle() {
        toggleApply((boolObject.limitChange || boolObject.breedChange || boolObject.sizeChange))
    }

    private fun toggleApply(dataChanged: Boolean) {
        binding.btnApply.isVisible = dataChanged
    }
}