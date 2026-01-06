package com.example.mininavigationapp.domain.usecase

import javax.inject.Inject
import kotlin.math.*

class CalculateDistanceUseCase @Inject constructor() : ICalculateDistanceUseCase {

    private val EARTH_RADIUS_METERS = 6371000.0
    // Haversine formula
    override operator fun invoke(
        startLat: Double,
        startLng: Double,
        endLat: Double,
        endLng: Double
    ): Double {
        val dLat = Math.toRadians(endLat - startLat)
        val dLng = Math.toRadians(endLng - startLng)

        val a = sin(dLat / 2).pow(2) +
                cos(Math.toRadians(startLat)) * cos(Math.toRadians(endLat)) *
                sin(dLng / 2).pow(2)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return EARTH_RADIUS_METERS * c
    }
}