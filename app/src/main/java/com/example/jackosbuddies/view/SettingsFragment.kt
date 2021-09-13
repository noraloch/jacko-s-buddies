package com.example.jackosbuddies.view

import android.os.Bundle
import android.util.Log
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

    private val TAG = "KAT-REPO"

    private lateinit var binding: FragmentSettingsBinding
    private val katViewModel by activityViewModels<KatViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
        initView()
    }

    private fun initView() = with(binding) {
        sliderLimit.value = katViewModel.limit.toFloat()
        var radioText = "full"
        var hasBreeds = false
        var sizeBool = false
        var limitBool = false
        var breedBool = false

        //toggling
        fun checkAllToggle() {
            Log.d(TAG, "checkAllToggle: ${limitBool || breedBool || sizeBool}")
            toggleApply((limitBool || breedBool || sizeBool))
        }

        // listeners
        btnApply.setOnClickListener {
//            toggleApply(false)
            katViewModel.fetchKatList(sliderLimit.value.toInt(), radioText, null, null, hasBreeds, 0)
        }

        sliderLimit.addOnChangeListener { _, value, _ ->
            if (katViewModel.limit != value.toInt()) {
                limitBool = katViewModel.limit != value.toInt()
            }
            checkAllToggle()
        }

        breedSwitch.setOnCheckedChangeListener { _, isChecked ->
            hasBreeds = isChecked
            if (katViewModel.hasBreeds != isChecked) {
                breedBool = katViewModel.hasBreeds != isChecked
            }
            checkAllToggle()
        }

        // Returns View.NO_ID if nothing is checked.
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            radioText = group.findViewById<View>(checkedId).transitionName
            if (katViewModel.size != radioText) {
                sizeBool = katViewModel.size != radioText
            }
            checkAllToggle()
        }

    }


    private fun toggleApply(dataChanged: Boolean) {
        binding.btnApply.isVisible = dataChanged
    }
}