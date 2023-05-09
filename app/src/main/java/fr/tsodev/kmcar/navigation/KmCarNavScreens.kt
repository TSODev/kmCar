package fr.tsodev.kmcar.navigation

import android.util.Log
import fr.tsodev.kmcar.screens.login.KmCarLoginScreen

enum class KmCarNavScreens {
    SplashScreen,
    KmCarLoginScreen,
    HomeScreen,
    AddNewKmEntry,
    KmSettings;
    companion object {
        fun fromRoute(route: String?) : KmCarNavScreens
         = when (route?.substringBefore("/")) {
             SplashScreen.name -> SplashScreen
            KmCarLoginScreen.name -> KmCarLoginScreen
            HomeScreen.name -> HomeScreen
            AddNewKmEntry.name -> AddNewKmEntry
            KmSettings.name -> KmSettings
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
         }
    }
}