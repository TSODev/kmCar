package fr.tsodev.kmcar.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.screens.CarIdentification
import fr.tsodev.kmcar.screens.home.HomeScreenViewModel
import kotlin.math.ceil

@Composable
fun ShowCarInfos(
    car: Car,
    navController: NavController,
    viewModel: HomeScreenViewModel,
) {
    var listOfEntries = emptyList<KmRec>()
    var totalKm = remember { mutableStateOf("0") }
    var progress = remember { mutableStateOf(0) }

    if (!viewModel.data.value.data.isNullOrEmpty()) {
        listOfEntries = viewModel.data.value.data!!.toList()
            .filter { kmRec -> kmRec.carId == car.id }
            .sortedBy { it.date }
        if (!listOfEntries.isNullOrEmpty()) {
            totalKm.value = listOfEntries.last().total
        } else {
            totalKm.value = "0"
        }

        progress.value =
            ceil((totalKm.value.toDouble() / car.limite.toDouble()) * 100).toInt()

        CarIdentification(car = car)
        ShowProgress(percent = progress.value.toInt())
        ShowTotalKm(
            navController = navController,
            car = car,
            total = totalKm.value
        )
        ShowProjectedKm(projected = "98765")
        ShowProjectedDate(projected = "15-10-2022")
    }
}
