package com.example.jackosbuddies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jackosbuddies.model.Kat
import com.example.jackosbuddies.model.Settings
import com.example.jackosbuddies.repo.KatRepo
import com.example.jackosbuddies.repo.SettingsRepo
import com.example.jackosbuddies.util.ApiState
import com.example.jackosbuddies.util.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class KatViewModel : ViewModel() {

    private val _katState = MutableLiveData<ApiState<List<Kat>>>()
    val katState: LiveData<ApiState<List<Kat>>>
        get() = _katState

    private val _settingsState = MutableLiveData<ApiState<Settings>>()
    val settingsState: LiveData<ApiState<Settings>>
        get() = _settingsState

    private var categoryIds: String? = null
    private var breedId: String? = null
    var size = ""
    var hasBreeds = false
    var limit = 0
    var page = 0
        set(value) {
            if (value > field && isNextPage) fetchKatList(this.limit, this.size, this.categoryIds, this.breedId, this.hasBreeds, this.page )
            field = value
        }

    private var isNextPage = true

    val TAG = "KatViewModel                                                                                        "


    fun fetchKatList(limit: Int, size: String, categoryIds: String?, breedId: String?, hasBreeds: Boolean, page: Int) {

//        Log.d(TAG, "fetchKatList: $settingsState")
//        Log.d(TAG, "fetchKatList: $limit , $categoryIds, $breedId")

        if (limit != this.limit || size != this.size || categoryIds != this.categoryIds || breedId != this.breedId || hasBreeds != this.hasBreeds){
            this.limit = limit
            this.size = size
            this.categoryIds = categoryIds
            this.breedId = breedId
            this.hasBreeds = hasBreeds
        }
        viewModelScope.launch(Dispatchers.IO) {
            KatRepo.getKatState(
                limit = limit.toString(),
                page = page.toString(),
                size = size,
                has_breeds = hasBreeds.toString(),
                order = Order.DESC.name,
                categoryIds = categoryIds.toString(),
                breedId = breedId.toString()
            ).collect { katState ->
                isNextPage =
                    !(katState is ApiState.Failure && katState.errorMsg == KatRepo.NO_DATA_FOUND)
                _katState.postValue(katState)
            }
        }
        fun fetchSettingsOptions() {
            viewModelScope.launch(Dispatchers.IO) {
                SettingsRepo.getSettingsState().collect {
                    _settingsState.postValue(it)
                }
            }
        }

    }
}