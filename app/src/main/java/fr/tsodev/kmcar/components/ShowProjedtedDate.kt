package fr.tsodev.kmcar.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.utils.DateUtils
import java.time.Instant
import java.util.Date


@Composable
fun ShowProjectedDate(
    car: Car,
    total: String
        ) {

    val today = DateUtils.convertDateToLocalDate(Date.from(Instant.now()))
    val dateFrom = DateUtils.convertStringToFrenchLocalDate(car.debut, "dd MMM yyyy")
    val dateTo = DateUtils.convertStringToFrenchLocalDate(car.fin, "dd MMM yyyy")

    val daysPast = DateUtils.calculateDateDifference(dateFrom, today)
    val daysTotal = DateUtils.calculateDateDifference(dateFrom, dateTo)

    val projectedDays = ((car.limite.toInt() * daysPast) / total.toInt())
//    val projected = DateUtils.convertDaysToLocalDate(projectedDays)
    val projected = dateFrom.plusDays(projectedDays)
    val projectedString = DateUtils.convertFrenchLocalDateToString(projected, "dd MMM yyyy")

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(140.dp),
//        shape = RoundedCornerShape(corner = CornerSize(2.dp)),
        shape = RoundedCornerShape(
            topEnd = 20.dp,
            topStart = 20.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(30.dp)
                    .background(color = MaterialTheme.colorScheme.secondaryContainer),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    text = "Fin de location :",
                )
                Text(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    text = car.fin
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 40.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    text = "Estimation"
                )
            }
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "Info icon",
                    modifier = Modifier.size(100.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )

                if (projected != null) {
                    Text(
                        text = "$projectedString",
                        //
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold
                        ),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

