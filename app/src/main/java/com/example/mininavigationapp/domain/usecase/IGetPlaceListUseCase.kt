package com.example.mininavigationapp.domain.usecase

import com.example.mininavigationapp.domain.model.Place

interface IGetPlaceListUseCase {
    suspend operator fun invoke(): List<Place>

}