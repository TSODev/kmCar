package fr.tsodev.kmcar.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import fr.tsodev.kmcar.MainActivity.Companion.lastKmRec
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.screen.KMRecordViewModel
import fr.tsodev.kmcar.screen.KmCarScreen
import java.time.Instant
import java.util.Date
import java.util.UUID

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavController) {

//    val kmRecordViewModel: KMRecordViewModel by viewModels()
//    val kmRecordList = kmRecordViewModel.kmRecordList
//    var totalKM = 0.0
//    var lastKmRec = KmRec(UUID.randomUUID(), Date.from(Instant.now()), 1, 0.0, 0.0)
//    try {
//        lastKmRec = kmRecordList.value.last()
//    } catch (e: Exception) {
//        e.message?.let { Log.d("EXCEPTION", it) }
//    }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        KmCarScreen(navController)
    }
}
