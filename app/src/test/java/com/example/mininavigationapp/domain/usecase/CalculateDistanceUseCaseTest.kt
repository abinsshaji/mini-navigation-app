package com.example.mininavigationapp.domain.usecase

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CalculateDistanceUseCaseTest {
    private val useCase = CalculateDistanceUseCase()

    @Test
    fun test_start_end_distance() {
        // Use two known points
        val startLat = 57.706558
        val startLng = 11.940218
        val endLat = 57.706420
        val endLng = 11.939519

        // approximate expected distance in meters.
        val expectedDistance = 44.0

        val actualDistance = useCase(startLat, startLng, endLat, endLng)

        assertEquals(expectedDistance, actualDistance, 1.0)
    }

    @Test
    fun test_same_start_end_distance() {
        val lat = 57.706558
        val lng = 57.706558

        val actualDistance = useCase(lat, lng, lat, lng)

        assertEquals(0.0, actualDistance, 0.0)
    }
}