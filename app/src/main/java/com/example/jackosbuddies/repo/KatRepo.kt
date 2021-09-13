package com.example.jackosbuddies.repo

import android.util.Log
import com.example.jackosbuddies.repo.remote.RetrofitInstance
import com.example.jackosbuddies.util.ApiState
import com.example.jackosbuddies.util.Order
import kotlinx.coroutines.flow.flow

object KatRepo {
    private const val TAG = "KAT-REPO"

    const val NO_DATA_FOUND = "No data found."
    private val katService by lazy { RetrofitInstance.katService }

    fun getKatState(
        limit: String,
        page: String,
        size: String?,
        order: String,
        has_breeds: String?,
        breedId: String?,
        categoryIds: String?
    ) = flow {
        emit(ApiState.Loading)

//        Log.d(TAG, "getKatState: katService.getKatImages(limit, page, size, order)")
        val queryMap = listOfNotNull(
            limit?.let { "limit" to it },
            page?.let { "page" to it },
            order?.let {"order" to it},
            size?.let { "size" to it },
            has_breeds?.let {"has_breeds" to it},
//            breedId?.let { "breed_id" to it },
//            categoryIds?.let { "category_ids" to it }
        ).toMap()

        val katResponse = katService.getKatImages(queryMap)

        Log.d(TAG, "size in endpoint = $size")
        Log.d(TAG, "has_breeds in endpoint = $has_breeds")
        Log.d(TAG, "getKatState: katResponse = ${katResponse.body()}")

        val state = if (katResponse.isSuccessful) {
//            Log.d(TAG, "getKatState: katResponse.isSuccessful")
            if (katResponse.body().isNullOrEmpty()) {
//                Log.d(TAG, "getKatState: Failure(No data found.)")
                ApiState.Failure(NO_DATA_FOUND)
            } else {
//                Log.d(TAG, "getKatState: Success(katResponse.body()!!)")
                ApiState.Success(katResponse.body()!!)
            }
        } else {
//            Log.d(TAG, "getKatState: Failure(Error fetching data.)")
            ApiState.Failure("Error fetching data.")
        }

//        Log.d(TAG, "getKatState: emit(state)")
        emit(state)
    }

}