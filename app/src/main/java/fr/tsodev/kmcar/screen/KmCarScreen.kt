package fr.tsodev.kmcar.screen

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import fr.tsodev.kmcar.R
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.navigation.KmCarNavScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KmCarScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                        },
                navigationIcon = {
                    IconButton(onClick = {
                        /* TODO Notification Button */
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(route = KmCarNavScreens.AddNewKmEntry.name)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },

        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = KmCarNavScreens.AddNewKmEntry.name)
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        },
        content = { innerPadding ->
            KmMainContent( innerPadding = innerPadding)

        }
    )
}


//@Preview(showBackground = true)
//@Composable
//fun kmCarScreenPreview() {
//    kmCarScreen()
//}