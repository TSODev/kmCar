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
import fr.tsodev.kmcar.components.KmListOfCars
import fr.tsodev.kmcar.screens.KmAddCar
import fr.tsodev.kmcar.screens.KmCarScreen
import fr.tsodev.kmcar.screens.KmCreateCar
import fr.tsodev.kmcar.screens.KmImportCar
import fr.tsodev.kmcar.screens.KmList

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

                composable(KmCarNavScreens.AddNewKmEntry.name + "/{carId}") {navBackStackEntry ->
                    val carId = navBackStackEntry.arguments?.getString("carId")
                    carId?.let {
                        AddNewKmEntry(navController = navController, it)
                    }
                }

               composable(KmCarNavScreens.KmList.name + "/{carId}") {navBackStackEntry ->
                   val carId = navBackStackEntry.arguments?.getString("carId")
                    val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
                   carId?.let {carId ->
                       KmList(
                            navController = navController,
                            viewModel = homeScreenViewModel,
                            carId = carId)
                   }
               }

        composable(KmCarNavScreens.KmCarScreen.name + "/{carId}") {navBackStackEntry ->
            val carId = navBackStackEntry.arguments?.getString("carId")
            val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
            carId?.let {carId ->
                KmCarScreen(
                    navController = navController,
                    viewModel = homeScreenViewModel,
                    carId = carId)
            }
        }

                composable(KmCarNavScreens.KmAddCar.name) {
                    KmAddCar(navController = navController)
                }

        composable(KmCarNavScreens.KmImportCar.name) {
            KmImportCar(navController = navController)
        }

        composable(KmCarNavScreens.KmCreateCar.name) {
            KmCreateCar(navController = navController)
        }

        composable(KmCarNavScreens.KmListOfCars.name) {
            val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
            KmListOfCars(navController = navController, viewModel = homeScreenViewModel)
        }
    }



}