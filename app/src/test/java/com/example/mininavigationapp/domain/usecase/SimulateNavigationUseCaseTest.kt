package com.example.mininavigationapp.domain.usecase

import app.cash.turbine.test
import com.example.mininavigationapp.domain.model.Place
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SimulateNavigationUseCaseTest {

    private lateinit var useCase: SimulateNavigationUseCase
    private val calculateDistanceUseCase: ICalculateDistanceUseCase = mockk()

    @Before
    fun setup() {
        useCase = SimulateNavigationUseCase(calculateDistanceUseCase)
    }

    // Mock Data
    private val start = Place(1, "Start", latitude = 59.3293, longitude = 18.0686)
    private val dest = Place(2, "End", latitude = 59.3326, longitude = 18.0649)

    @Test
    fun test_simulation_emit_progress() = runTest {
        // distance of 40 meters. At 20m/s, it will be 2 steps + arrival emissions.
        coEvery { calculateDistanceUseCase(any(), any(), any(), any()) } returns 40.0

        useCase(dest, start).test {
            // T=0s: 40m remaining, 2s ETA
            val first = awaitItem()
            assertEquals(40.0, first.distanceMeters, 0.1)
            assertEquals(2, first.etaSeconds)

            // T=1s: 20m remaining, 1s ETA
            val second = awaitItem()
            assertEquals(20.0, second.distanceMeters, 0.1)
            assertEquals(1, second.etaSeconds)

            // T=2s: 0m remaining, 0s ETA
            val third = awaitItem()
            assertEquals(0.0, third.distanceMeters, 0.1)
            assertEquals(0, third.etaSeconds)

            awaitComplete()
        }
    }

    @Test
    fun test_simulation_short_distance() = runTest {
        //Only 5 meters to travel
        coEvery { calculateDistanceUseCase(any(), any(), any(), any()) } returns 5.0

        useCase(dest, start).test {
            assertEquals(5.0, awaitItem().distanceMeters, 0.1)
            assertEquals(0.0, awaitItem().distanceMeters, 0.1) // Should jump to 0
            awaitComplete()
        }
    }

    @Test
    fun test_emulation_error() = runTest {
        val errorMessage = "GPS signal lost"
        coEvery {
            calculateDistanceUseCase(any(), any(), any(), any())
        } throws RuntimeException(errorMessage)


        useCase(dest, start).test {
            val error = awaitError()
            assertTrue(error is RuntimeException)
            assertEquals(errorMessage, error.message)
        }
    }
}