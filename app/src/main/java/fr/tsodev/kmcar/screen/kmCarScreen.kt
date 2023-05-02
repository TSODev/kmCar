package fr.tsodev.kmcar.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import fr.tsodev.kmcar.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun kmCarScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                        },
                navigationIcon = {
                    IconButton(onClick = { /* TODO doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO doSomething() */ }) {
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
            ExtendedFloatingActionButton(
                onClick = { /* TODO fab click handler */ }
            ) {
                Icon(imageVector = Icons.Rounded.AddCircle,
                    contentDescription = "Icon")
            }
        },
        content = { innerPadding ->
            kmMainContent(innerPadding = innerPadding)

        }
    )
}

@Preview(showBackground = true)
@Composable
fun kmCarScreenPreview() {
    kmCarScreen()
}