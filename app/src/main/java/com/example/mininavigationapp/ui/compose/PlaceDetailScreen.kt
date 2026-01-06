package com.example.mininavigationapp.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mininavigationapp.presentation.uistate.PlaceDetailUiState
import com.example.mininavigationapp.presentation.viewmodel.PlaceDetailViewModel
import com.example.mininavigationapp.utils.NavigationFormatter

@Composable
fun PlaceDetailScreen(
    onStartNav: (Int) -> Unit,
    viewModel: PlaceDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Route the state to the stateless content
    PlaceDetailContent(
        state = state,
        onStartNav = onStartNav
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailContent(
    state: PlaceDetailUiState,
    onStartNav: (Int) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Details") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                is PlaceDetailUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is PlaceDetailUiState.Success -> {
                    Text(text = state.name, style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${state.latitude} , ${state.longitude}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = NavigationFormatter.formatDistance(state.distanceInMeters),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = { onStartNav(state.placeId) }) {
                        Text("Start Navigation")
                    }
                }

                is PlaceDetailUiState.Error -> {
                    Text("Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Detail Success")
@Composable
fun PlaceDetailPreviewSuccess() {
    MaterialTheme {
        PlaceDetailContent(
            state = PlaceDetailUiState.Success(
                placeId = 1,
                name = "Eiffel Tower",
                distanceInMeters = 25000.55,
                latitude = 48.8584,
                longitude = 2.2945
            ),
            onStartNav = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Detail Loading")
@Composable
fun PlaceDetailPreviewLoading() {
    MaterialTheme {
        PlaceDetailContent(
            state = PlaceDetailUiState.Loading,
            onStartNav = {}
        )
    }
}