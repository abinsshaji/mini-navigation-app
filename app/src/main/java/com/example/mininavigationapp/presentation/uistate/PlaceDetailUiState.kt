package com.example.mininavigationapp.presentation.uistate

sealed interface PlaceDetailUiState {
    data object Loading : PlaceDetailUiState

    data class Success(
        val placeId : Int,
        val name: String,
        val distanceInMeters: Double,
        val latitude: Double,
        val longitude: Double
    ) : PlaceDetailUiState

    data class Error(
        val message: String
    ) : PlaceDetailUiState
}
