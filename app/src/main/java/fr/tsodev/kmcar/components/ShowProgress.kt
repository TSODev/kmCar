package fr.tsodev.kmcar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ShowProgress(percent : Int = 12) {

    // score is percentage from 0 to 100

    val gradient = Brush.linearGradient(colors = listOf(
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.secondary
    ))

    val progressFactor by remember(percent) {
        mutableStateOf(percent*0.01f)
    }
    Row(modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth()
        .height(20.dp)
        .border(
            width = 2.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.secondaryContainer,
                    MaterialTheme.colorScheme.secondaryContainer
                )
            ),
            shape = RoundedCornerShape(34.dp)
        )
        .clip(
            RoundedCornerShape(
                topStartPercent = 50,
                topEndPercent = 50,
                bottomStartPercent = 50,
                bottomEndPercent = 50
            )
        )
        .background(Color.Transparent),
    verticalAlignment = Alignment.CenterVertically) {
        Button(
            contentPadding = PaddingValues(1.dp),
            onClick = { },
            modifier = Modifier.fillMaxWidth(progressFactor)
                .background(brush = gradient),
            enabled = false,
            elevation = null,
        colors = buttonColors(
                    contentColor = Color.Transparent,
                    disabledContainerColor =  Color.Transparent)) {
            
        }
    }
}