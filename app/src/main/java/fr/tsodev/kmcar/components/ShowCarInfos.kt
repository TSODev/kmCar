package fr.tsodev.kmcar.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
//    viewModel: HomeScreenViewModel,
) {
    var listOfEntries = emptyList<KmRec>()
    val totalKm = remember { mutableStateOf("0") }
    val progress = remember { mutableStateOf(0) }
    var hasKmEntries = false

    val viewModel: HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>()


    if (!viewModel.kmEntry.value.data.isNullOrEmpty()) {
        listOfEntries = viewModel.kmEntry.value.data!!.toList()
            .filter { kmRec -> kmRec.carId == car.id }
            .sortedBy { it.date }
        if (!listOfEntries.isEmpty()) {
            totalKm.value = listOfEntries.last().total
            hasKmEntries = true
        } else {
            hasKmEntries = false
            totalKm.value = "0"
        }
    }
        progress.value =
            ceil((totalKm.value.toDouble() / car.limite.toDouble()) * 100).toInt()

        CarIdentification(navController = navController, car = car)
        if (car.location) {
            ShowProgress(percent = progress.value)
        }
        Spacer(modifier = Modifier.height(20.dp))
        ShowTotalKm(
            navController = navController,
            car = car,
            total = totalKm.value
        )
        if (car.location)
           if (hasKmEntries) {
                Spacer(modifier = Modifier.height(20.dp))
                ShowProjectedKm(car = car, total = totalKm.value)
                Spacer(modifier = Modifier.height(20.dp))
                ShowProjectedDate(car = car, total = totalKm.value)
            }

}
