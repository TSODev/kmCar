package fr.tsodev.kmcar.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.ImportExport
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import fr.tsodev.kmcar.R
import fr.tsodev.kmcar.navigation.KmCarNavScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KmAddCar(navController: NavController) {

    var selector : MutableState<Boolean> = remember { mutableStateOf(true) }
    var expanded: MutableState<Boolean> = remember { mutableStateOf(false) }

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
                            },
                            enabled = true
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
                    shape = RoundedCornerShape(10.dp),
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
                        Row(modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .height(80.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically)
                        {
                            Button(onClick = { navController.navigate(KmCarNavScreens.KmImportCar.name) },
                                modifier = Modifier.size(width = 200.dp, height = 100.dp),
                                enabled = true,
                                shape = RoundedCornerShape(corner = CornerSize(2.dp)),
       //                         colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                                )
                            {
                                Text(text = "Ajouter avec un code",
                                    textAlign = TextAlign.Center )
                            }
                            Spacer(modifier = Modifier.width(30.dp))
                            Button(onClick = { navController.navigate(KmCarNavScreens.KmCreateCar.name)},
                                modifier = Modifier.size(width = 200.dp, height = 100.dp),
                                enabled = true,
                                shape = RoundedCornerShape(corner = CornerSize(2.dp)),
      //                          colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                            )
                            {
                                Text(text = "Créer un nouveau véhicule",
                                       textAlign = TextAlign.Center )
                            }
                        }


                    }
                }
            }
        }
    )
}