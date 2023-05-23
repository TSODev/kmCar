package fr.tsodev.kmcar.screens.home


import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.tsodev.kmcar.data.firestore.DataOrException
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.repository.FirestoreRepository
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: FirestoreRepository): ViewModel() {
    val kmEntry: MutableState<DataOrException<List<KmRec>, Boolean, Exception>>
            = mutableStateOf(DataOrException(listOf(), true,Exception("")))
    val cars: MutableState<DataOrException<List<Car>, Boolean, Exception>>
            = mutableStateOf(DataOrException(listOf(), true,Exception("")))

    init {
        getAllEntriesFromDatabase()
        getAllCarsFromDatabase()
    }

    private fun getAllEntriesFromDatabase() {
        viewModelScope.launch {
            kmEntry.value.loading = true
            kmEntry.value = repository.getAllEntriesFromDatabase()
            if (!kmEntry.value.data.isNullOrEmpty()) kmEntry.value.loading = false
        }
        Log.d("GET", "getAllEntriesFromDatabase: ${kmEntry.value.data?.toList().toString()}")

    }

    private fun getAllCarsFromDatabase() {
        viewModelScope.launch {
            cars.value.loading = true
            cars.value = repository.getAllCarsFromDatabase()
            if (!cars.value.data.isNullOrEmpty()) cars.value.loading = false
        }
        Log.d("GET", "getAllEntriesFromDatabase: ${cars.value.data?.toList().toString()}")

    }

    private fun getCarById(carId: String) {
        viewModelScope.launch {
            cars.value.loading = true
            cars.value = repository.getDocumentsForCar(carId)
            if (!cars.value.data.isNullOrEmpty()) cars.value.loading = false
        }
        Log.d("GET", "getAllEntriesFromDatabase: ${cars.value.data?.toList().toString()}")
    }




}