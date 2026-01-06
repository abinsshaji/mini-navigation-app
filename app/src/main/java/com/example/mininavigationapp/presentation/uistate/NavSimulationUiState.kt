sealed interface NavSimulationUiState {
    data object Idle : NavSimulationUiState

    data object Loading : NavSimulationUiState

    data class Navigating(
        val remainingDistance: Double,
        val etaSeconds: Int,
        val destinationName: String
    ) : NavSimulationUiState

    data object Arrived : NavSimulationUiState

    data class Error(val message: String) : NavSimulationUiState
}