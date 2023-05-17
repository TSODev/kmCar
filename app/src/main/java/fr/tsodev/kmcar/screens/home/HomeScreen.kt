package fr.tsodev.kmcar.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fr.tsodev.kmcar.components.KmListOfCars
import fr.tsodev.kmcar.components.widget.LoadingProgressBar
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.screens.KmAddCar
import fr.tsodev.kmcar.screens.KmCarScreen
import fr.tsodev.kmcar.utils.Constants

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavController,
                viewModel: HomeScreenViewModel = hiltViewModel()) {

    val currentUser = Firebase.auth.uid.toString()
//    val currentCar: MutableState<Car> = remember { mutableStateOf(Constants.NO_CAR_FOUND) }
    val currentCar: Car = Constants.NO_CAR_FOUND
    val loading: MutableState<Boolean> = remember { mutableStateOf(true) }

    var listOfEntries = emptyList<KmRec>()
    var listOfCars : List<Car> = emptyList<Car>()
    var listOfCarsForCurrentUser : List<Car> = emptyList<Car>()

    loading.value = viewModel.cars.value.loading!!
    if (!loading.value) {
        listOfCars = viewModel.cars.value.data!!
        if (!listOfCars.isNullOrEmpty()) {
            listOfCarsForCurrentUser =
                listOfCars.toList().filter { car -> car.userId == currentUser }
        }
    }



    // A surface container using the 'background' color from the theme
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            KmCarScreen(navController, viewModel)
            if (loading.value) {
                LoadingProgressBar()
            } else {
                if (listOfCarsForCurrentUser.isNullOrEmpty()) {
                    KmAddCar(navController = navController)
                } else {
                    if (listOfCarsForCurrentUser.size == 1) {
                        KmCarScreen(
                            navController = navController,
                            viewModel = viewModel,
                            carId = listOfCarsForCurrentUser[0].id
                        )
                    } else {
                        KmListOfCars(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}
