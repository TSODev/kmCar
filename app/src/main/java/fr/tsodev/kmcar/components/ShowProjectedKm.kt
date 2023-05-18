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
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.utils.DateUtils.Companion.calculateDateDifference
import fr.tsodev.kmcar.utils.DateUtils.Companion.convertDateToLocalDate
import fr.tsodev.kmcar.utils.DateUtils.Companion.convertLocalDateToDate
import fr.tsodev.kmcar.utils.DateUtils.Companion.convertStringToFrenchLocalDate
import fr.tsodev.kmcar.utils.DateUtils.Companion.dateFromString
import fr.tsodev.kmcar.utils.DateUtils.Companion.nbOfDayBetweenTwoDates
import java.time.Instant
import java.util.Date

@Composable
fun ShowProjectedKm (
                        car: Car,
                        total: String) {

    val today = convertDateToLocalDate(Date.from(Instant.now()))
    val dateFrom = convertStringToFrenchLocalDate(car.debut, "dd MMM yyyy")
    val dateTo = convertStringToFrenchLocalDate(car.fin, "dd MMM yyyy")

    val daysPast = calculateDateDifference(dateFrom, today)
    val daysTotal = calculateDateDifference(dateFrom, dateTo)

    val projected = ((total.toInt() * daysTotal) / daysPast).toInt()


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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(color = MaterialTheme.colorScheme.secondaryContainer),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    text = "Kilométrage limite :",
                    )
                Text(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    text = car.limite + " Kms"
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
                    imageVector = Icons.Default.ArrowRightAlt,
                    contentDescription = "Info icon",
                    modifier = Modifier.size(100.dp),
                    MaterialTheme.colorScheme.secondary
                )

                //           Spacer(modifier = Modifier.size(30.dp))
                if (projected != null) {
                    CompteurKm(projected.toString())
                }
            }
        }
    }
}
