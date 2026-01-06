import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mininavigationapp.domain.model.Place
import com.example.mininavigationapp.presentation.uistate.PlacesUiState
import com.example.mininavigationapp.presentation.viewmodel.PlacesViewModel
import com.example.mininavigationapp.ui.compose.PlaceItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceListScreen(
    onPlaceClick: (Int) -> Unit,
    viewModel: PlacesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Just pass the state into the Content function
    PlaceListContent(
        state = state,
        onPlaceClick = onPlaceClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceListContent(
    state: PlacesUiState,
    onPlaceClick: (Int) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Places") }) }
    ) { padding ->
        when (state) {
            is PlacesUiState.Success -> {
                LazyColumn(modifier = Modifier.padding(padding)) {
                    items(state.places) { place ->
                        PlaceItem(place = place, onClick = { onPlaceClick(place.id) })
                        HorizontalDivider()
                    }
                }
            }

            is PlacesUiState.Loading -> {
                LinearProgressIndicator(modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding))
            }

            is PlacesUiState.Error -> {
                Text("Error: ${state.message}", modifier = Modifier.padding(padding))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlaceListPreview() {
    MaterialTheme {
        PlaceListContent(
            state = PlacesUiState.Success(
                places = listOf(
                    Place(1, "Eiffel Tower", latitude = 48.8584, longitude = 2.2945),
                    Place(2, "Colosseum", latitude = 41.8902, longitude = 12.4922)
                )
            ),
            onPlaceClick = {}
        )
    }
}