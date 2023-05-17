package fr.tsodev.kmcar.navigation

import android.util.Log
import fr.tsodev.kmcar.screens.login.KmCarLoginScreen

enum class KmCarNavScreens {
    SplashScreen,
    KmCarLoginScreen,
    HomeScreen,
    KmCarScreen,
    AddNewKmEntry,
    KmSettings,
    KmList,
    KmListOfCars,
    KmAddCar,
    KmImportCar,
    KmCreateCar;
    companion object {
        fun fromRoute(route: String?) : KmCarNavScreens
         = when (route?.substringBefore("/")) {
             SplashScreen.name -> SplashScreen
            KmCarLoginScreen.name -> KmCarLoginScreen
            HomeScreen.name -> HomeScreen
            KmCarScreen.name -> KmCarScreen
            AddNewKmEntry.name -> AddNewKmEntry
            KmSettings.name -> KmSettings
            KmList.name -> KmList
            KmListOfCars.name -> KmListOfCars
            KmAddCar.name -> KmAddCar
            KmImportCar.name -> KmImportCar
            KmCreateCar.name -> KmCreateCar
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
         }
    }
}