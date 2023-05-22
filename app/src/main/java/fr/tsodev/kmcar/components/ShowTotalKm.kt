package fr.tsodev.kmcar.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.navigation.KmCarNavScreens

@Composable
fun ShowTotalKm(navController: NavController,
                car: Car,
//                viewModel: HomeScreenViewModel,
                total : String,
//                context: Context = LocalContext.current.applicationContext
                ) {
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
        Column(

        ) {
            Row (
                modifier = Modifier.fillMaxWidth()
                    .height(30.dp)
                    .background(color = MaterialTheme.colorScheme.secondaryContainer),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
                    ){
                Text(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    text = car.fabricant,
                )
                Text(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    text = car.modele
                )
            }

            Row(
                modifier = Modifier
                    //               .padding(12.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clickable {
                        /* TODO click on Total -> Show List of entries*/
                        //                   Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
                        navController.navigate(KmCarNavScreens.KmList.name + "/${car.id}")
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Icon(
                    imageVector = Icons.Default.DirectionsCar,
                    contentDescription = "Info icon",
                    modifier = Modifier.size(100.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )

//            Spacer(modifier = Modifier.size(30.dp))
                CompteurKm(total)
            }
        }
    }
}

