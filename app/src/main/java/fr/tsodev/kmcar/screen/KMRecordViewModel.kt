package fr.tsodev.kmcar.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.repository.kmRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class KMRecordViewModel @Inject constructor(private val repository: kmRecordRepository) :ViewModel() {
    private val _kmRecordList = MutableStateFlow<List<KmRec>>(emptyList())
    val kmRecordList = _kmRecordList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllRecords().distinctUntilChanged()
                .collect { listOfkmRecords ->
                    if (listOfkmRecords.isNullOrEmpty()) {
                        Log.d("LIST", "Empty List ")
                    } else {
                        _kmRecordList.value = listOfkmRecords
                    }

                }
        }
    }

    fun addRecord(kmRec: KmRec) = viewModelScope.launch { repository.addRecord(kmRec) }
    fun updateRecord(kmRec: KmRec) = viewModelScope.launch { repository.updateRecord(kmRec) }
    fun deleteRecord(kmRec: KmRec) = viewModelScope.launch { repository.deleteRecord(kmRec) }
}