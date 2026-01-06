package com.example.mininavigationapp.domain.usecase

import com.example.mininavigationapp.data.repository.IPlaceRepository
import com.example.mininavigationapp.domain.model.Place
import javax.inject.Inject

class GetMyLocationUseCase @Inject constructor(private val placeRepository: IPlaceRepository) :
    IGetMyLocationUseCase {
    override suspend fun invoke(): Place = placeRepository.getMyLocation()
}
