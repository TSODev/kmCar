package fr.tsodev.kmcar.screens.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.tsodev.kmcar.R
import fr.tsodev.kmcar.navigation.KmCarNavScreens
import fr.tsodev.kmcar.screens.KmMainContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KmSettings (navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                },
                navigationIcon = {
//                    IconButton(onClick = {
//                        /* TODO Notification Button */
//                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.Menu,
//                            contentDescription = "Localized description"
//                        )
//                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(route = KmCarNavScreens.KmSettings.name)
                    }) {
                        Icon(
                            modifier = Modifier.size(50.dp),
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings"
                        )
                    }
                },
            )
        },

        content = { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                Text(text = "Settings")
            }
        }
    )
}
