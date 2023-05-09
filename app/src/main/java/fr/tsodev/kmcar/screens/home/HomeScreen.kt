package fr.tsodev.kmcar.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.tsodev.kmcar.screens.KmCarScreen

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavController,
                viewModel: HomeScreenViewModel = hiltViewModel()) {

    // A surface container using the 'background' color from the theme
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KmCarScreen(navController, viewModel)
        }
    }
}
