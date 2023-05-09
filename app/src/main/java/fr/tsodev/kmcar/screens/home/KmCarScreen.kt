package fr.tsodev.kmcar.screens

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fr.tsodev.kmcar.R
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.navigation.KmCarNavScreens
import fr.tsodev.kmcar.screens.home.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KmCarScreen(navController: NavController,
                viewModel: HomeScreenViewModel) {

    val TAG = "KMCARSCREEN"
    var totalKm = remember { mutableStateOf("0") }
    val currentUser = Firebase.auth.uid.toString()
    var listOfEntries = emptyList<KmRec>()

    if (!viewModel.data.value.data.isNullOrEmpty()) {
        listOfEntries = viewModel.data.value.data!!.toList()
            .filter { kmRec -> kmRec.userId == currentUser }
            .sortedBy { it.date }

        Log.d(TAG, "KmMainContent: ${listOfEntries.toString()}")
        totalKm.value = listOfEntries.last().total
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                        },
                navigationIcon = {
//                    IconButton(onClick = {
//                        /* TODO Notification Button */
//                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.Menu,
//                            contentDescription = "Localized description"
//                        )
//                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(route = KmCarNavScreens.KmSettings.name)
                    }) {
                        Icon(
                            modifier = Modifier.size(50.dp),
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings"
                        )
                    }
                },
            )
        },

        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = KmCarNavScreens.AddNewKmEntry.name)
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        },
        content = { innerPadding ->
            KmMainContent( innerPadding = innerPadding, totalKm = totalKm.value)

        }
    )
}


//@Preview(showBackground = true)
//@Composable
//fun kmCarScreenPreview() {
//    kmCarScreen()
//}