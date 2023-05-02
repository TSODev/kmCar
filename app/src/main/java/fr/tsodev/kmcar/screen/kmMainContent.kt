package fr.tsodev.kmcar.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun kmMainContent(innerPadding: PaddingValues) {
    Surface(
        modifier = androidx.compose.ui.Modifier.padding(innerPadding)
            .fillMaxSize(),
        color = Color.LightGray
    ) {
        Column() {
            Text(text = "Contenu")
        }
    }
}