import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mininavigationapp.ui.compose.PlaceDetailScreen

object Routes {
    const val PLACE_LIST = "list"
    const val PLACE_DETAIL = "detail/{placeId}"
    const val NAV_SIMULATION = "sim/{placeId}"

    fun detail(id: Int) = "detail/$id"
    fun sim(id: Int) = "sim/$id"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.PLACE_LIST
    ) {
        // List screen
        composable(Routes.PLACE_LIST) {
            PlaceListScreen(
                onPlaceClick = { id ->
                    navController.navigate(Routes.detail(id))
                }
            )
        }

        // Details Screen.
        composable(
            route = Routes.PLACE_DETAIL,
            arguments = listOf(
                navArgument("placeId") { type = NavType.IntType }
            )
        ) {
            PlaceDetailScreen(
                onStartNav = { id ->
                    navController.navigate(Routes.sim(id))
                }
            )
        }

        // Nav Simulation Screen.
        composable(
            route = Routes.NAV_SIMULATION,
            arguments = listOf(
                navArgument("placeId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            // Extract the ID to pass it into the simulation's LaunchedEffect
            val placeId = backStackEntry.arguments?.getInt("placeId") ?: 0

            NavSimulationScreen(
                placeId = placeId,
                onArrived = {
                    // Pop back to the list and clear the stack so "Back" doesn't return to sim
                    navController.popBackStack(Routes.PLACE_LIST, inclusive = false)
                }
            )
        }
    }
}