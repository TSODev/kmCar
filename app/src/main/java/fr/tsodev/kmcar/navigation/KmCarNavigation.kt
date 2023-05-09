package fr.tsodev.kmcar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.tsodev.kmcar.screens.AddNewKmEntry
import fr.tsodev.kmcar.screens.SplashScreen
import fr.tsodev.kmcar.screens.home.HomeScreen
import fr.tsodev.kmcar.screens.home.HomeScreenViewModel
import fr.tsodev.kmcar.screens.login.KmCarLoginScreen
import fr.tsodev.kmcar.screens.settings.KmSettings
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun KmCarNavigation () {
    val navController = rememberNavController()
    NavHost(navController = navController,
            startDestination = KmCarNavScreens.SplashScreen.name) {

                composable(KmCarNavScreens.SplashScreen.name) {
                    SplashScreen(navController = navController)
                }

        composable(KmCarNavScreens.KmSettings.name) {
                       KmSettings(navController = navController)
                   }

        composable(KmCarNavScreens.KmCarLoginScreen.name) {
                     KmCarLoginScreen(navController = navController)
               }
                composable(KmCarNavScreens.HomeScreen.name) {
                    val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
                    HomeScreen(navController = navController)
                }

                composable(KmCarNavScreens.AddNewKmEntry.name) {
                    AddNewKmEntry(navController = navController)
                }
    }
}