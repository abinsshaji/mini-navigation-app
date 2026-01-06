package com.example.mininavigationapp.data.datasource

import com.example.mininavigationapp.domain.model.Place

interface IPlaceDataSource {
    suspend fun fetchPlaces(): List<Place>
}