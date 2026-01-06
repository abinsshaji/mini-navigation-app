package com.example.mininavigationapp.domain.usecase

import com.example.mininavigationapp.data.repository.IPlaceRepository
import javax.inject.Inject

class GetPlaceListUseCase @Inject constructor(
val placeRepository: IPlaceRepository
) : IGetPlaceListUseCase {
    override suspend operator fun invoke() = placeRepository.getPlaces().sortedBy {
        it.name
    }
}