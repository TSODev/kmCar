package fr.tsodev.kmcar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.tsodev.kmcar.screens.AddNewKmEntry
import fr.tsodev.kmcar.screens.home.HomeScreen

@Composable
fun KmCarNavigation () {
    val navController = rememberNavController()
    NavHost(navController = navController,
            startDestination = KmCarNavScreens.HomeScreen.name) {
                composable(KmCarNavScreens.HomeScreen.name) {
                    HomeScreen(navController = navController)
                }

                composable(KmCarNavScreens.AddNewKmEntry.name) {
                    AddNewKmEntry(navController = navController)
                }
    }
}