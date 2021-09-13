package com.example.jackosbuddies.repo.remote

import com.example.jackosbuddies.model.Breed
import com.example.jackosbuddies.model.Category
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface SettingsService {

    @Headers("x-api-key: aee94851-c66c-4640-88b7-8be981e56ed9")
    @GET("v1/breeds")
    suspend fun getKatBreeds(): Response<List<Breed>>

    @Headers("x-api-key: 1fa2c5c0-305d-4305-8edc-2efcbf37b9b4")
    @GET("v1/categories")
    suspend fun getKatCategories(): Response<List<Category>>
}