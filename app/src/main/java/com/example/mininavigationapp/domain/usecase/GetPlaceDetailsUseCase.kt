package com.example.mininavigationapp.domain.usecase

import com.example.mininavigationapp.data.repository.IPlaceRepository
import com.example.mininavigationapp.domain.model.Place
import javax.inject.Inject

class GetPlaceDetailsUseCase @Inject constructor(
    private val placeRepository: IPlaceRepository
) : IGetPlaceDetailsUseCase {
    override suspend operator fun invoke(id: Int) = placeRepository.getPlaceById(id)
}