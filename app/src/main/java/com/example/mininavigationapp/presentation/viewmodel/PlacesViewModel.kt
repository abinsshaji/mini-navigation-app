package com.example.mininavigationapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mininavigationapp.domain.usecase.IGetPlaceListUseCase
import com.example.mininavigationapp.presentation.uistate.PlacesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val getPlaces: IGetPlaceListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PlacesUiState>(PlacesUiState.Loading)
    val state: StateFlow<PlacesUiState> = _state

    init {
        viewModelScope.launch {
            _state.value = PlacesUiState.Success(getPlaces())
        }
    }
}