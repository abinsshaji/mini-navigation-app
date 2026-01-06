package com.example.mininavigationapp.data.repository

import com.example.mininavigationapp.data.datasource.FakePlaceDataSource
import com.example.mininavigationapp.domain.model.Place
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    val placeDataSource: FakePlaceDataSource
) : IPlaceRepository {
    override suspend fun getPlaces(): List<Place> = withContext(Dispatchers.Default) {
        placeDataSource.fetchPlaces()
    }

    override suspend fun getPlaceById(id: Int) = withContext(Dispatchers.Default) {
        placeDataSource.fetchPlaces().first { it.id == id }
    }

    override suspend fun getMyLocation(): Place = Place(
        id = 2555,
        name = "My Location",
        latitude = 59.3293,
        longitude = 18.0686
    )
}