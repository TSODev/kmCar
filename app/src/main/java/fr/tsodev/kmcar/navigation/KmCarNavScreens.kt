package fr.tsodev.kmcar.navigation

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
    KmCreateCar,
    KmCarInfos;
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
            KmCarInfos.name -> KmCarInfos
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
         }
    }
}