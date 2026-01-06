package com.example.mininavigationapp.domain.usecase

import com.example.mininavigationapp.domain.model.Place

interface IGetMyLocationUseCase {
    operator fun invoke(): Place
}