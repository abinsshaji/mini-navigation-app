package com.example.mininavigationapp.domain.usecase

import com.example.mininavigationapp.domain.model.Place
import javax.inject.Inject

class GetMyLocationUseCase @Inject constructor() : IGetMyLocationUseCase {
    override fun invoke(): Place {
        return Place(
            id = 2555,
            name = "My Location",
            latitude = 59.3293,
            longitude = 18.0686
        )
    }
}