package com.example.mininavigationapp.domain.usecase

import com.example.mininavigationapp.domain.model.Place

interface IGetPlaceDetailsUseCase {
    suspend operator fun invoke(id: Int) : Place
}