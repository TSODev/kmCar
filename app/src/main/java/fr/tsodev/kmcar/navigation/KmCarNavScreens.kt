package fr.tsodev.kmcar.navigation

enum class KmCarNavScreens {
    HomeScreen,
    AddNewKmEntry;
    companion object {
        fun fromRoute(route: String?) : KmCarNavScreens
         = when (route?.substringBefore("/")) {
             HomeScreen.name -> HomeScreen
            AddNewKmEntry.name -> AddNewKmEntry
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
         }
    }
}