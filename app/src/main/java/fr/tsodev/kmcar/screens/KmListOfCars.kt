package fr.tsodev.kmcar.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.ImportExport
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import fr.tsodev.kmcar.R
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.navigation.KmCarNavScreens
import fr.tsodev.kmcar.repository.FirestoreRepository
import fr.tsodev.kmcar.screens.home.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KmListOfCars(
//    listOfCars: List<Car>,
//    selectedCar: MutableState<Car>,
    navController: NavController,
    viewModel: HomeScreenViewModel,
    ) {

    val TAG = "KMCARSCREEN"
    val viewModel: HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
    var listOfCars : List<Car> = emptyList<Car>()
    var listOfCarsForCurrentUser : List<Car> = emptyList<Car>()
    val currentUser = FirebaseAuth.getInstance().uid
    if (!viewModel.cars.value.loading!!) {
        listOfCars = viewModel.cars.value.data!!
        if (!listOfCars.isNullOrEmpty()) {
            listOfCarsForCurrentUser =
                listOfCars.toList().filter { car -> car.userId == currentUser }
        }
    }


    val expanded = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            expanded.value = !expanded.value
                        },
                    ) {
                        Icon(
                            modifier = Modifier.size(50.dp),
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "More",
                            tint = Color.White
                        )
                    }
                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Importer un véhicule existant") },
                            onClick = { navController.navigate(KmCarNavScreens.KmImportCar.name) },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.ImportExport,
                                    contentDescription = null
                                )
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Ajouter un véhicule") },
                            onClick = { navController.navigate(KmCarNavScreens.KmCreateCar.name) },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.AddCircle,
                                    contentDescription = null
                                )
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Déconnexion") },
                            onClick = {
                                FirebaseAuth.getInstance().signOut()
                                navController.navigate(KmCarNavScreens.KmCarLoginScreen.name)
//                                Toast.makeText(context, "Deconnexion", Toast.LENGTH_SHORT).show()
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Logout,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF3F51B5),
                    titleContentColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        },
        bottomBar = {
            BottomAppBar() {

            }
        },
        content = { innerPadding ->
            Surface(modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
            ) {
                Card(modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(680.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "Choisir un véhicule")
                        Divider()
                        listOfCarsForCurrentUser.forEach { car ->
                            ListItem(
                                modifier = Modifier.clickable {
                                    navController.navigate(KmCarNavScreens.KmCarScreen.name + "/${car.id}")
                                },
                                headlineContent = { Text(text = car.plaque)},
                                supportingContent = { Text(text = car.fabriquant + " " + car.modele)},
  //                              trailingContent = { Text(text = car.fabriquant)},
                                leadingContent = {
                                    Icon(
                                        Icons.Filled.DirectionsCar,
                                        contentDescription = "Icon"
                                    )
                                }
                            )
                            Divider()
                        }


                    }
                }
            }



        }
    )

}
