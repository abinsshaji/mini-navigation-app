package com.example.mininavigationapp.presentation.viewmodel

import NavSimulationUiState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mininavigationapp.domain.usecase.IGetMyLocationUseCase
import com.example.mininavigationapp.domain.usecase.IGetPlaceDetailsUseCase
import com.example.mininavigationapp.domain.usecase.ISimulateNavigationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val simulateNavigationUseCase: ISimulateNavigationUseCase,
    private val getPlaceByIdUseCase: IGetPlaceDetailsUseCase,
    private val getMyLocationUseCase: IGetMyLocationUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<NavSimulationUiState>(NavSimulationUiState.Idle)
    val uiState: StateFlow<NavSimulationUiState> = _uiState.asStateFlow()

    private var navJob: Job? = null

    init {
        startNavigation()
    }

    fun startNavigation() {
        val placeId: Int? = savedStateHandle["placeId"]
        if (placeId == null) {
            _uiState.value = NavSimulationUiState.Error("Place not found")
            return
        }
        navJob?.cancel()
        navJob = viewModelScope.launch {
            _uiState.value = NavSimulationUiState.Loading
            try {
                val destination = getPlaceByIdUseCase(placeId)
                val start = getMyLocationUseCase()

                simulateNavigationUseCase(destination, start)
                    .collect { update ->
                        if (update.distanceMeters <= 0) {
                            _uiState.value = NavSimulationUiState.Arrived
                        } else {
                            _uiState.value = NavSimulationUiState.Navigating(
                                remainingDistance = update.distanceMeters,
                                etaSeconds = update.etaSeconds,
                                destinationName = destination.name
                            )
                        }
                    }
            } catch (e: Exception) {
                _uiState.value = NavSimulationUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}