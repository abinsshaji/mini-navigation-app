package com.example.mininavigationapp.data.repository

import com.example.mininavigationapp.domain.model.Place

interface IPlaceRepository {
    suspend fun getPlaces(): List<Place>
    suspend fun getPlaceById(id: Int): Place
    suspend fun getMyLocation(): Place
}