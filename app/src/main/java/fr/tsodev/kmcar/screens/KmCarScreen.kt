package fr.tsodev.kmcar.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.ImportExport
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fr.tsodev.kmcar.R
import fr.tsodev.kmcar.components.KmListOfCars
import fr.tsodev.kmcar.components.ShowCarInfos
import fr.tsodev.kmcar.components.ShowProgress
import fr.tsodev.kmcar.components.ShowProjectedDate
import fr.tsodev.kmcar.components.ShowProjectedKm
import fr.tsodev.kmcar.components.ShowTotalKm
import fr.tsodev.kmcar.components.widget.LoadingProgressBar
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.navigation.KmCarNavScreens
import fr.tsodev.kmcar.screens.home.HomeScreenViewModel
import fr.tsodev.kmcar.utils.Constants
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun KmCarScreen(navController: NavController,
                viewModel: HomeScreenViewModel,
                carId: String) {

    val TAG = "KMCARSCREEN"
    val viewModel: HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
    var listOfCars : List<Car> = emptyList<Car>()
    var filteredListOfCars : List<Car> = emptyList<Car>()
    var currentCar: Car = Constants.NO_CAR_FOUND
    val loading: MutableState<Boolean> = remember { mutableStateOf(true)}

    loading.value = viewModel.cars.value.loading!!
    if (!loading.value) {
        listOfCars = viewModel.cars.value.data!!
        if (!listOfCars.isNullOrEmpty()) {
            currentCar =
                listOfCars.filter { car -> car.id == carId }.first()
        }
    }


    var expanded = remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.secondary,
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
                            tint = MaterialTheme.colorScheme.secondary
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
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        },
//        bottomBar = {
//            BottomAppBar() {
//
//            }
//        },

        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.size(80.dp),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                shape = CircleShape,
                onClick = {
                navController.navigate(route = KmCarNavScreens.AddNewKmEntry.name + "/${currentCar.id}")
            }) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(35.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        },
        content = { innerPadding ->
            Surface(modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize()
                ) {
                Card(modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(680.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                ) {
                    if (loading.value) {
                        LoadingProgressBar()
                    } else {
                        Column(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            ShowCarInfos(currentCar, navController, viewModel)
                        }
                    }
                }
            }



        }
    )
}



//@Preview(showBackground = true)
//@Composable
//fun kmCarScreenPreview() {
//    kmCarScreen()
//}