package com.example.mininavigationapp.domain.usecase

interface ICalculateDistanceUseCase {
    operator fun invoke(
        startLat: Double,
        startLng: Double,
        endLat: Double,
        endLng: Double
    ): Double
}