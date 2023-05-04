package fr.tsodev.kmcar.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.tsodev.kmcar.components.DrawDottedLine
import fr.tsodev.kmcar.components.ShowProgress
import fr.tsodev.kmcar.components.ShowTotalKm
import fr.tsodev.kmcar.utils.Constants

@SuppressLint("StateFlowValueCalledInComposition", "ComposableNaming")
@Composable
fun KmMainContent(innerPadding: PaddingValues) {

    var totalKm = 0.0

    Surface(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.onPrimary
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            DrawDottedLine(pathEffect = Constants.DOT_LINE)
            ShowProgress( percent = 75)
            ShowTotalKm(total = 0.0)
            DrawDottedLine(pathEffect = Constants.DOT_LINE)


        }
    }
}