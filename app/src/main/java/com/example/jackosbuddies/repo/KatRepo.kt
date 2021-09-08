package com.example.jackosbuddies.repo

import android.util.Log
import com.example.jackosbuddies.model.Kat
import com.example.jackosbuddies.repo.remote.RetrofitInstance
import com.example.jackosbuddies.util.ApiState
import com.example.jackosbuddies.util.Order
import kotlinx.coroutines.flow.flow

object KatRepo {
    private const val TAG = "KAT-REPO"

    private val katService by lazy { RetrofitInstance.katService }

    fun getKatState(
        limit: Int,
        page: Int = 1,
        order: Order = Order.DESC
    ) = flow<ApiState<List<Kat>>> {
        Log.d(TAG, "getKatState: emit(ApiState.Loading")
        emit(ApiState.Loading)

        Log.d(TAG, "getKatState: katService.getKatImages(limit, page, order)")
        val katResponse = katService.getKatImages(limit, page, order)
        Log.d(TAG, "getKatState:katResponse (meta-data) = $katResponse")

        val state = if (katResponse.isSuccessful) {
            Log.d(TAG, "getKatState:is successful")

            if (katResponse.body().isNullOrEmpty()) {
                Log.d(TAG, "getKatState: Failure(No data found)")
                ApiState.Failure("No data found")
            } else {
                Log.d(TAG, "getKatState: ${katResponse.body()}!!)")
                // !! means, cannot be null - sa we previously eliminated the null case
                ApiState.Success(katResponse.body()!!)
            }
        } else {
            Log.d(TAG, "getKatState:failure")
            ApiState.Failure("Error fetching data")
        }
        Log.d(TAG, "getKatState: about to - emit(state) -")
        emit(state)
    }

}