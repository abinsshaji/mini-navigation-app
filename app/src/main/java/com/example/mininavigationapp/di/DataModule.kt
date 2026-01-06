package com.example.mininavigationapp.di

import com.example.mininavigationapp.data.datasource.FakePlaceDataSource
import com.example.mininavigationapp.data.datasource.IPlaceDataSource
import com.example.mininavigationapp.data.repository.IPlaceRepository
import com.example.mininavigationapp.data.repository.PlaceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindPlaceDataSource(dataSource: FakePlaceDataSource): IPlaceDataSource

    @Binds
    @Singleton
    abstract fun bindPlaceRepository(impl: PlaceRepositoryImpl): IPlaceRepository
}