package com.example.mininavigationapp.domain.model

data class Place(
    val id: Int,
    val name: String,
    val description: String = "can add description later",
    val latitude: Double,
    val longitude: Double
)
