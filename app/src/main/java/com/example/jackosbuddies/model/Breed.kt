package com.example.jackosbuddies.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Breed(
    val adaptability: Int?,
    val affectionLevel: Int?,
    val altNames: String?,
    val cfaUrl: String?,
    val childFriendly: Int?,
    val countryCode: String?,
    val countryCodes: String?,
    val description: String?,
    val dogFriendly: Int?,
    val energyLevel: Int?,
    val experimental: Int?,
    val grooming: Int?,
    val hairless: Int?,
    val healthIssues: Int?,
    val hypoallergenic: Int?,
    val id: String?,
    val indoor: Int?,
    val intelligence: Int?,
    val lap: Int?,
    val lifeSpan: String?,
    val name: String?,
    val natural: Int?,
    val origin: String?,
    val rare: Int?,
    val referenceImageId: String?, val rex: Int?, val sheddingLevel: Int?, val shortLegs: Int?, val socialNeeds: Int?,
    val strangerFriendly: Int?,
    val suppressedTail: Int?,
    val temperament: String?,
    val vetstreetUrl: String?,
    val vocalisation: Int?,
    val weight: Weight?,
    val wikipediaUrl: String
)