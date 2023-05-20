package fr.tsodev.kmcar.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import fr.tsodev.kmcar.R
import fr.tsodev.kmcar.components.InputField
import fr.tsodev.kmcar.navigation.KmCarNavScreens
import fr.tsodev.kmcar.repository.KmRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KmImportCar( navController: NavController) {

    val importedCarId = rememberSaveable { mutableStateOf("") }
    
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
        content = { innerPadding ->
            Surface(modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize()
            ) {
                Card(modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize(),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 50.dp, horizontal = 15.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 15.dp),
                            text = "Importer un véhicule",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        Divider(color = MaterialTheme.colorScheme.onSurface)
                        Spacer(modifier = Modifier.width(200.dp))
                        Text(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth(),
                            text = "vous pouvez obtenir le code d'identification d'un véhicule " +
                                    "depuis l'application , en selectionnant le véhicule , " +
                                    "et en affichant ces caractéristiques. " +
                                    "(Cliquez sur la plaque mineralogique du véhicule)",
                            style = MaterialTheme.typography.titleSmall,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Light
                        )

                        Spacer(modifier = Modifier.width(200.dp))
                        InputField(
                            modifier = Modifier.fillMaxWidth()
                                .padding(vertical = 60.dp),
                            valueState = importedCarId,
                            labelId = "Identification",
                            keyboardType = KeyboardType.Ascii,
                            enabled = true,
                            isSingleLine = false,
                            visible = true,
                            imageVector = Icons.Filled.CardTravel
                        )
                        Spacer(modifier = Modifier.width(120.dp))
                        Button(
                            modifier = Modifier.width(350.dp),
                            onClick = {
                                KmRepository(FirebaseFirestore.getInstance()).importCar(importedCarId.value)
                                    .addOnSuccessListener {
                                        navController.navigate(KmCarNavScreens.HomeScreen.name)
                                    }
                                    .addOnFailureListener {
                                        Log.d("IMPORTCAR", "KmImportCar: Erreur d'importation Firestore")
                                    }
                            }
                        ) {
                            Text(text = "Importer ce véhicule")
                        }
                    }
                }
            }
        }
    )
}