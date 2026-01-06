package com.example.mininavigationapp.presentation.uistate

sealed interface PlaceDetailUiState {
    data object Loading : PlaceDetailUiState// No data? Use data object

    data class Success(
        val placeId : Int,
        val name: String,
        val distanceInMeters: Double,
        val latitude: Double,
        val longitude: Double
    ) : PlaceDetailUiState // Data? Use data class

    data class Error(
        val message: String
    ) : PlaceDetailUiState
}