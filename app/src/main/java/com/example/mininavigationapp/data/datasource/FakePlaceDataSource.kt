package com.example.mininavigationapp.data.datasource

import com.example.mininavigationapp.domain.model.Place
import javax.inject.Inject

class FakePlaceDataSource @Inject constructor() : IPlaceDataSource {

    override suspend fun fetchPlaces(): List<Place> = listOf(
        Place(1, "Home",  latitude = 59.3493, longitude = 18.1686),
        Place(2, "Office", latitude = 59.3380, longitude = 18.0967),
        Place(3, "Park",  latitude = 59.2925, longitude = 18.0649),
        Place(4, "Mall",  latitude = 59.3151, longitude = 18.0703),
        Place(5, "Museum", latitude = 59.3075, longitude = 18.0711),
        Place(6, "Stockholm", latitude = 61.3380, longitude = 22.0967),
        Place(7, "Malmo", latitude = 55.3380, longitude = 16.0967)


    )
}