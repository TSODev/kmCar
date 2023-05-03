package fr.tsodev.kmcar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShowTotalKm( total : Double) {
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .height(180.dp)
        .background(MaterialTheme.colorScheme.onSecondary),
        shape = RoundedCornerShape(corner = CornerSize(2.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row( modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Info,
                contentDescription = "Info icon",
                modifier = Modifier.size(40.dp),
                tint = Color(0xFF781A56)
                )
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "En Cours",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.size(60.dp))
            CompteurKm(total)
        }
    }

}