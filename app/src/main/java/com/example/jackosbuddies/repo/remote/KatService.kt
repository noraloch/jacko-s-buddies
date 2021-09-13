package com.example.jackosbuddies.repo.remote

import com.example.jackosbuddies.model.Kat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface KatService {

    @Headers("x-api-key: aee94851-c66c-4640-88b7-8be981e56ed9")
    @GET("/v1/images/search")
    suspend fun getKatImages(@QueryMap queryMap: Map<String, String>): Response<List<Kat>>

}
