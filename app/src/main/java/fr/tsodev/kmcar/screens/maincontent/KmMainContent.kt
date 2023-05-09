package fr.tsodev.kmcar.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fr.tsodev.kmcar.components.DrawDottedLine
import fr.tsodev.kmcar.components.ShowProgress
import fr.tsodev.kmcar.components.ShowTotalKm
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.screens.home.HomeScreenViewModel
import fr.tsodev.kmcar.utils.Constants


@SuppressLint("StateFlowValueCalledInComposition", "ComposableNaming")
@Composable
fun KmMainContent(innerPadding: PaddingValues,
                    totalKm: String) {

    val TAG = "KMMAINCONTENT"
//    var totalKm = remember { mutableStateOf("0") }
//    val currentUser = Firebase.auth.uid.toString()
//    var listOfEntries = emptyList<KmRec>()
//
//    if (!viewModel.data.value.data.isNullOrEmpty()) {
//        listOfEntries = viewModel.data.value.data!!.toList()
//            .filter { kmRec -> kmRec.userId == currentUser }
//            .sortedBy { it.date }
//
//        Log.d(TAG, "KmMainContent: ${listOfEntries.toString()}")
//        totalKm.value = listOfEntries.last().total
//    }

            Surface(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    DrawDottedLine(pathEffect = Constants.DOT_LINE)
                    ShowProgress( percent = 75)
                    ShowTotalKm(total = totalKm)
                    DrawDottedLine(pathEffect = Constants.DOT_LINE)


                }
            }

}