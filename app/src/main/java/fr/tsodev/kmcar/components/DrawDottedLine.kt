package fr.tsodev.kmcar.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp


@Composable
fun DrawDottedLine(pathEffect: PathEffect?) {
    Spacer(modifier = Modifier.height(26.dp))
    Canvas(modifier = Modifier.fillMaxWidth()
        .height(1.dp)) {
            drawLine(color = Color.DarkGray,
            start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = 0f ),
                pathEffect = pathEffect
            )
    }
    Spacer(modifier = Modifier.height(26.dp))
}