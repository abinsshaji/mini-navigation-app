package com.example.mininavigationapp.domain.usecase

import com.example.mininavigationapp.domain.model.NavigationUpdate
import com.example.mininavigationapp.domain.model.Place
import kotlinx.coroutines.flow.Flow

interface ISimulateNavigationUseCase {
    operator fun invoke(destination: Place, startLocation: Place): Flow<NavigationUpdate>
}