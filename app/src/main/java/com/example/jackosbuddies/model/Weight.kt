package com.example.jackosbuddies.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weight(
    val imperial: String?,
    val metric: String?
)