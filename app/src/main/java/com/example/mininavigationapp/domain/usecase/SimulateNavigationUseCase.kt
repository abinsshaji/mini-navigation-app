package com.example.mininavigationapp.domain.usecase

import com.example.mininavigationapp.domain.model.NavigationUpdate
import com.example.mininavigationapp.domain.model.Place
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SimulateNavigationUseCase @Inject constructor(
    val calculateDistanceUseCase: ICalculateDistanceUseCase
) : ISimulateNavigationUseCase {
    // mocking speed as 20 meters per second.
    private val MOCK_SPEED_MPS = 20.0

    override operator fun invoke(destination: Place, startLocation: Place): Flow<NavigationUpdate> =
        flow {
            // find total distance.
            var remainingDistance = calculateDistanceUseCase(
                startLat = startLocation.latitude, startLng = startLocation.longitude,
                destination.latitude, destination.longitude
            )
            // loop until arrive at destination.
            while (remainingDistance > 0) {
                // Calculate ETA: Time = Distance / Speed.
                val etaSeconds = (remainingDistance / MOCK_SPEED_MPS).toInt()

                // Emit the current progress.
                emit(
                    NavigationUpdate(
                        distanceMeters = remainingDistance,
                        etaSeconds = etaSeconds
                    )
                )

                // Wait for 1 second for the next update.
                delay(1000)

                //subtract the distance covered in 1 second
                remainingDistance -= MOCK_SPEED_MPS

                // negative distance check
                if (remainingDistance < 0) remainingDistance = 0.0
            }

            // arrived
            emit(NavigationUpdate(distanceMeters = 0.0, etaSeconds = 0))
        }.flowOn(Dispatchers.Default)

}