package com.example.jackosbuddies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jackosbuddies.model.Kat
import com.example.jackosbuddies.repo.KatRepo
import com.example.jackosbuddies.util.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class KatViewModel : ViewModel() {

    private val _katState = MutableLiveData<ApiState<List<Kat>>>()
    val katState: LiveData<ApiState<List<Kat>>>
        get() = _katState

    var limit = 0
    var page = 0
        set(value) {
            if (value > field && isNextPage) fetchKatList(limit)
            field = value
        }
    var isNextPage = true

    fun fetchKatList(limit: Int) {
        this.limit = limit
        viewModelScope.launch(Dispatchers.IO) {
            KatRepo.getKatState(limit, page).collect { katState ->
                isNextPage =
                    !(katState is ApiState.Failure && katState.errorMsg == KatRepo.NO_DATA_FOUND)
                _katState.postValue(katState)
            }
        }
    }
}