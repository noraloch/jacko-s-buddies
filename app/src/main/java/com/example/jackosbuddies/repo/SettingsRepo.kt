package com.example.jackosbuddies.repo

import com.example.jackosbuddies.model.Settings
import com.example.jackosbuddies.repo.remote.RetrofitInstance
import com.example.jackosbuddies.util.ApiState
import kotlinx.coroutines.flow.flow

object SettingsRepo {
    private const val TAG = "SETTING-REPO"

    private const val NO_DATA_FOUND = "No data found."

    private lateinit var settingsState: ApiState<Settings>

    private val settingsService by lazy { RetrofitInstance.settingsService }

    fun getSettingsState() = flow {
        emit(ApiState.Loading)

        val katBreedsResponse = settingsService.getKatBreeds()
        val katCategoriesResponse = settingsService.getKatCategories()

        if (katBreedsResponse.isSuccessful && katCategoriesResponse.isSuccessful) {
            if (katBreedsResponse.body().isNullOrEmpty() || katCategoriesResponse.body()
                    .isNullOrEmpty()
            ) {
                emit(ApiState.Failure(NO_DATA_FOUND))
            } else {
                settingsState = ApiState.Success(
                    Settings(
                        katCategoriesResponse.body()!!,
                        katBreedsResponse.body()!!
                    )
                )
            }
        } else emit(ApiState.Failure("Error fetching data."))

        emit(settingsState)
    }

}