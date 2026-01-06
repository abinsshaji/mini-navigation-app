package com.example.mininavigationapp.di

import com.example.mininavigationapp.domain.usecase.CalculateDistanceUseCase
import com.example.mininavigationapp.domain.usecase.GetMyLocationUseCase
import com.example.mininavigationapp.domain.usecase.GetPlaceDetailsUseCase
import com.example.mininavigationapp.domain.usecase.GetPlaceListUseCase
import com.example.mininavigationapp.domain.usecase.ICalculateDistanceUseCase
import com.example.mininavigationapp.domain.usecase.IGetMyLocationUseCase
import com.example.mininavigationapp.domain.usecase.IGetPlaceDetailsUseCase
import com.example.mininavigationapp.domain.usecase.IGetPlaceListUseCase
import com.example.mininavigationapp.domain.usecase.ISimulateNavigationUseCase
import com.example.mininavigationapp.domain.usecase.SimulateNavigationUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindsGetPlaceListUseCase(impl: GetPlaceListUseCase) : IGetPlaceListUseCase

    @Binds
    abstract fun bindsGetPlaceDetailsUseCase(impl: GetPlaceDetailsUseCase) : IGetPlaceDetailsUseCase

    @Binds
    abstract fun bindsCalculateDistanceUseCase(impl : CalculateDistanceUseCase) : ICalculateDistanceUseCase

    @Binds
    abstract fun bindsSimulateNavigationUseCase(impl: SimulateNavigationUseCase) : ISimulateNavigationUseCase

    @Binds
    abstract fun bindsGetMyLocationUseCase(impl: GetMyLocationUseCase): IGetMyLocationUseCase

}