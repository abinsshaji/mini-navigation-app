package com.example.mininavigationapp.data.repository

import com.example.mininavigationapp.data.datasource.FakePlaceDataSource
import com.example.mininavigationapp.domain.model.Place
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    val placeDataSource: FakePlaceDataSource
)  : IPlaceRepository {
    override suspend fun getPlaces(): List<Place> {
        return placeDataSource.fetchPlaces()
    }

    override suspend fun getPlaceById(id: Int) = placeDataSource.fetchPlaces().first { it.id == id }
}