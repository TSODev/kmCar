package fr.tsodev.kmcar.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.tsodev.kmcar.model.Car
import org.checkerframework.checker.units.qual.A

@Composable
fun CarIdentification(
    car: Car,
    ) {

//    var checked = remember { mutableStateOf(false) }
    Card(modifier = Modifier
        .padding(horizontal = 30.dp, vertical = 15.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(CornerSize(6.dp)) ,
        border = BorderStroke(4.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = car.plaque,
                modifier = Modifier.padding(horizontal = 45.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black
            )
        }

    }
}
