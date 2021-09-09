package com.example.jackosbuddies.repo.remote

import com.example.jackosbuddies.model.Kat
import com.example.jackosbuddies.util.Order
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KatService {

    @Headers("x-api-key: aee94851-c66c-4640-88b7-8be981e56ed9")
    @GET("/v1/images/search")
    // suspend for multi threading like async
    suspend fun getKatImages(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("size") size: String,
        @Query("order") order: Order
    ): Response<List<Kat>>
}
