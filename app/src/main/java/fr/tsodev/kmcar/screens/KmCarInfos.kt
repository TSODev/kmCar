package fr.tsodev.kmcar.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import fr.tsodev.kmcar.R
import fr.tsodev.kmcar.components.QrCodeComposable
import fr.tsodev.kmcar.components.ShowProgress
import fr.tsodev.kmcar.components.widget.LoadingProgressBar
import fr.tsodev.kmcar.components.widget.SimpleDialog
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.navigation.KmCarNavScreens
import fr.tsodev.kmcar.repository.KmRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KmCarInfos(
    navController: NavController,
    carId: String
) {
    val TAG = "CARINFOS"
    var showDialog = remember { mutableStateOf(false)}
    var showLoadingProgressBar = remember { mutableStateOf(false)}
    var car: MutableState<Car> = remember {mutableStateOf(fr.tsodev.kmcar.utils.Constants.NO_CAR_FOUND)}
    val data = KmRepository(FirebaseFirestore.getInstance())
        .getDocumentsForCar(carId = carId)
        .addOnSuccessListener { carList ->
            if (!carList.isEmpty) {
                car.value = carList.documents.first().toObject(Car::class.java)!!
                Log.d(TAG, "KmCarInfos: ${carList.documents.first().toObject(Car::class.java)}")
            }
        }
        .addOnFailureListener { e ->
            Log.d(TAG, "KmCarInfos: Error : ${e.message}")
        }

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
                navigationIcon = {
                    IconButton(onClick = {
                        /* TODO Notification Button */
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.size(80.dp),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                shape = CircleShape,
                onClick = {
                    showDialog.value = true
                }) {
                Icon(imageVector = Icons.Filled.Delete,
                    contentDescription = "Add",
                    modifier = Modifier.size(35.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        },
        content = { innerPadding ->
            if (showLoadingProgressBar.value) {
                LoadingProgressBar()
            }
            if (showDialog.value)
                AlertDialog(
                    onDismissRequest = {
                        showDialog.value = false
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Column( modifier = Modifier.padding(16.dp)) {
                            Text(text = "Vous aller supprimer ce véhicule et tous les enregistrements qui lui sont attachés")
                            Spacer(modifier = Modifier.height(24.dp))
                            TextButton(onClick = {
                                showDialog.value= false
                                showLoadingProgressBar.value = true
                                val data = KmRepository(FirebaseFirestore.getInstance())
                                    .deleteCar(carId = carId)
                                    .addOnSuccessListener {
                                        navController.navigate(KmCarNavScreens.HomeScreen.name)
                                    }
                                    .addOnFailureListener {
                                        Log.d(TAG, "KmCarInfos: Erreur Suppression" )
                                    }

                                        }) {
                                            Text(text = "Confirme")
                                            }
                        }
                    }
                }
            Surface(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(10.dp),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.secondary
                    )
                ) {

                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Identification du véhicule",
                            modifier = Modifier.padding(20.dp),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Text(
                            text = carId,
                            modifier = Modifier.padding(20.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center

                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly

                    ) {
                        OutlinedTextField(
                            value = car.value.plaque,
                            onValueChange = {},
                            label = { Text("Immatriculation") },
                            readOnly = true // Set the text field to read-only
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly

                    ) {
                        OutlinedTextField(
                            value = car.value.fabricant,
                            onValueChange = {},
                            label = { Text("Modèle") },
                            readOnly = true // Set the text field to read-only
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly

                    ) {
                        OutlinedTextField(
                            value = car.value.modele,
                            onValueChange = {},
                            label = { Text("Modèle") },
                            readOnly = true // Set the text field to read-only
                        )
                    }
                    if (car.value.location) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround

                        ) {
                            OutlinedTextField(
                                modifier = Modifier.width(280.dp),
                                value = car.value.debut,
                                onValueChange = {},
                                label = { Text("Début") },
                                readOnly = true // Set the text field to read-only
                            )
                        }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround

                            ) {
                            OutlinedTextField(
                                modifier = Modifier.width(280.dp),
                                value = car.value.fin,
                                onValueChange = {},
                                label = { Text("Fin") },
                                readOnly = true // Set the text field to read-only
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly

                        ) {
                            OutlinedTextField(
                                value = car.value.limite,
                                onValueChange = {},
                                label = { Text("Kilometrage") },
                                readOnly = true // Set the text field to read-only
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp),
                        horizontalArrangement = Arrangement.Center

                    ) {
                        QrCodeComposable(data = carId)
                    }



                }


            }
        }
    )
}