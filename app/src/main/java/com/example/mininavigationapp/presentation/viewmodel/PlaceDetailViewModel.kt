package com.example.mininavigationapp.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mininavigationapp.domain.usecase.ICalculateDistanceUseCase
import com.example.mininavigationapp.domain.usecase.IGetMyLocationUseCase
import com.example.mininavigationapp.domain.usecase.IGetPlaceDetailsUseCase
import com.example.mininavigationapp.presentation.uistate.PlaceDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val getPlaceByIdUseCase: IGetPlaceDetailsUseCase,
    private val calculateDistanceUseCase: ICalculateDistanceUseCase,
    private val getMyLocationUseCase: IGetMyLocationUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Internal mutable state
    private val _uiState = MutableStateFlow<PlaceDetailUiState>(PlaceDetailUiState.Loading)

    // External immutable state for the UI to observe
    val uiState: StateFlow<PlaceDetailUiState> = _uiState.asStateFlow()

    init {
        loadPlaceDetails()
    }

    private fun loadPlaceDetails() {
        // Retrieve navigation argument "placeId"
        val placeId: Int? = savedStateHandle["placeId"]

        if (placeId == null) {
            _uiState.value = PlaceDetailUiState.Error("Place not found")
            return
        }

        viewModelScope.launch {
            try {
                val destination = getPlaceByIdUseCase.invoke(placeId)
                val start = getMyLocationUseCase()

                val distanceInMeters = calculateDistanceUseCase(
                    startLat = start.latitude,
                    startLng = start.longitude,
                    endLat = destination.latitude,
                    endLng = destination.longitude
                )

                // Update state to Success
                _uiState.value = PlaceDetailUiState.Success(
                    placeId = destination.id,
                    name = destination.name,
                    distanceInMeters = distanceInMeters,
                    latitude = destination.latitude,
                    longitude = destination.longitude
                )
            } catch (e: Exception) {
                _uiState.value = PlaceDetailUiState.Error(e.message ?: "Failed to load place details")
            }
        }
    }
}