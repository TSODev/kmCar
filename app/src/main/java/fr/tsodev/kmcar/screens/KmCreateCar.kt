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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirportShuttle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ViewDay
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fr.tsodev.kmcar.R
import fr.tsodev.kmcar.components.InputField
import fr.tsodev.kmcar.components.widget.DatePickerButton
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.navigation.KmCarNavScreens
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KmCreateCar( navController: NavController) {

    val immatriculation = rememberSaveable { mutableStateOf("") }
    val fabricant = rememberSaveable { mutableStateOf("") }
    val modele = rememberSaveable { mutableStateOf("") }
    val isLocation = rememberSaveable { mutableStateOf(false) }
    val limite = rememberSaveable { mutableStateOf("0") }
    val debut = rememberSaveable { mutableStateOf("") }
    val fin = rememberSaveable { mutableStateOf("") }
    val valid = remember(immatriculation.value) {
        immatriculation.value.trim().isNotEmpty()
    }

    val icon: (@Composable () -> Unit)? = if (isLocation.value) {
        {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
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
                actions = {
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
        content = { innerPadding ->
            Surface(modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxSize(),
     //                 .height(880.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
//                        Card(
//                            modifier = Modifier
//                                .padding(5.dp)
//                                .fillMaxWidth(),
////                                .height(360.dp),
////                            shape = RoundedCornerShape(10.dp),
////                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
////                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
//                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
//                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxSize()
                                    .height(250.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top
                            ) {
                                InputField(
                                    //                    modifier = Modifier.focusRequester(carFocusRequest),
                                    valueState = immatriculation,
                                    labelId = "Plaque d'immatriculation",
                                    enabled = true,
                                    isSingleLine = true,
                                    imageVector = Icons.Default.ViewDay,
                                    keyboardType = KeyboardType.Ascii,
                                    onAction = KeyboardActions {
                                        if (!valid) return@KeyboardActions
                                        /* TODO */
                                    },
                                    visible = true
                                )
                                InputField(
                                    //                    modifier = Modifier.focusRequester(carFocusRequest),
                                    valueState = fabricant,
                                    labelId = "Marque",
                                    enabled = true,
                                    isSingleLine = true,
                                    imageVector = Icons.Default.AirportShuttle,
                                    keyboardType = KeyboardType.Ascii,
                                    onAction = KeyboardActions {
                                        if (!valid) return@KeyboardActions
                                        /* TODO */
                                    },
                                    visible = true
                                )
                                InputField(
                                    //                    modifier = Modifier.focusRequester(carFocusRequest),
                                    valueState = modele,
                                    labelId = "Modèle",
                                    enabled = true,
                                    isSingleLine = true,
                                    imageVector = Icons.Default.DirectionsCar,
                                    keyboardType = KeyboardType.Ascii,
                                    onAction = KeyboardActions {
                                        if (!valid) return@KeyboardActions
                                        /* TODO */
                                    },
                                    visible = true
                                )
                                Row(modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth()
                                    .height(80.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically){
                                    Text(text = "Location")
                                    Switch(
                                        checked = isLocation.value,
                                        onCheckedChange = {isLocation.value = it},
                                        colors = SwitchDefaults.colors(
                                            checkedTrackColor = MaterialTheme.colorScheme.primary,
                                        ),
                                        thumbContent = icon
                                    )
                                }
                                InputField(
                                    //                    modifier = Modifier.focusRequester(carFocusRequest),
                                    valueState = limite,
                                    labelId = "Kilometrage autorisé",
                                    enabled = true,
                                    isSingleLine = true,
                                    imageVector = Icons.Default.ArrowForward,
                                    keyboardType = KeyboardType.Number,
                                    onAction = KeyboardActions {
                                        if (!valid) return@KeyboardActions
                                        /* TODO */
                                    },
                                    visible = isLocation.value
                                )
                                DatePickerButton(
                                    modifier = Modifier.width(300.dp),
                                    valueState = debut,
                                    labelId = "Début de location",
                                    enabled = true,
                                    imageVector = Icons.Default.CalendarMonth,
                                    visible = isLocation.value
                                )
                                DatePickerButton(
                                    modifier = Modifier.width(300.dp),
                                    valueState = fin,
                                    labelId = "Fin de location",
                                    enabled = true,
                                    imageVector = Icons.Default.CalendarMonth,
                                    visible = isLocation.value
                                )
                                Spacer(modifier = Modifier.height(80.dp))
                                Button(
                                    modifier = Modifier.width(300.dp),
                                    enabled = true,
                                    shape = RoundedCornerShape(corner = CornerSize(2.dp)),
                           //         colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                                    onClick = {
                                        val vehicule = Car(
                                            UUID.randomUUID().toString(),
                                            FirebaseAuth.getInstance().uid.toString(),
                                            immatriculation.value,
                                            fabricant.value,
                                            modele.value,
                                            isLocation.value,
                                            limite.value,
                                            debut.value,
                                            fin.value
                                        )
                                        FirebaseFirestore.getInstance().collection("cars").add(vehicule)
                                            .addOnSuccessListener {
                                                navController.navigate(KmCarNavScreens.HomeScreen.name)
                                                Log.d("FBSTORE", "onCreate: ${it.id}")
                                            }
                                            .addOnFailureListener {
                                                Log.d("FBSTORE", "onCreate: (Failure) $it, for ${FirebaseAuth.getInstance().uid.toString()}")
                                            }
                                    }
                                )
                                {
                                    Text(text = "Enregistrer",
                                        textAlign = TextAlign.Center )
                                }
                            }
                        }

                }
            }
        }
    )
}

