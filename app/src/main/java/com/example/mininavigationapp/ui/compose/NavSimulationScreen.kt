import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mininavigationapp.presentation.viewmodel.NavigationViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mininavigationapp.utils.NavigationFormatter


@Composable
fun NavSimulationScreen(
    placeId: Int,
    onArrived: () -> Unit,
    viewModel: NavigationViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    NavSimContent(
        state = state,
        onArrived = onArrived
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavSimContent(
    state: NavSimulationUiState,
    onArrived: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Navigation") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (state) {
                is NavSimulationUiState.Idle,
                is NavSimulationUiState.Loading -> CircularProgressIndicator()

                is NavSimulationUiState.Navigating -> {
                    Text(state.destinationName, style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${NavigationFormatter.formatDistance(state.remainingDistance)} remaining",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Arriving in: ${NavigationFormatter.formatEta(state.etaSeconds)}",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
                    )
                }

                is NavSimulationUiState.Arrived -> {
                    Text("🏁 You have arrived!", style = MaterialTheme.typography.headlineLarge)
                    Button(onClick = onArrived, modifier = Modifier.padding(top = 16.dp)) {
                        Text("Finish")
                    }
                }

                is NavSimulationUiState.Error -> {
                    Text("Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NavSimPreview() {
    MaterialTheme {
        NavSimContent(
            state = NavSimulationUiState.Navigating(
                remainingDistance = 450.0,
                etaSeconds = 120,
                destinationName = "Stockholm City Hall"
            ),
            onArrived = {}
        )
    }
}