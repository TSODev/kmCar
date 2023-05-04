package fr.tsodev.kmcar.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.tsodev.kmcar.R
import fr.tsodev.kmcar.components.DrawDottedLine
import fr.tsodev.kmcar.components.InputField
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.utils.Constants
import fr.tsodev.kmcar.utils.DateFormater.Companion.DateToString
import java.time.Instant
import java.util.Date
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddNewKmEntry(navController: NavController) {

    val newKmEntryState = remember {
        mutableStateOf("")
    }

    val validEntryState = remember(newKmEntryState.value) {
        newKmEntryState.value.trim().isNotEmpty()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

  //  val kmRecordViewModel by viewModels(KMRecordViewModel)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },

                )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(it)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                val context = LocalContext.current

                Text(text = "Ajouter un nouvel enregistrement")
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .padding(25.dp)
                        .fillMaxWidth()
                        .height(80.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = DateToString(Date.from(Instant.now())),
                        onValueChange = {},
                        label = { Text(text = "Date actuelle") },
                        readOnly = true
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(25.dp)
                        .fillMaxWidth()
                        .height(80.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InputField(
                        valueState = newKmEntryState,
                        labelId = "Nouveau Kilometrage",
                        keyboardType = KeyboardType.Number,
                        enabled = true,
                        isSingleLine = true,
                        onAction = KeyboardActions {
                            if (!validEntryState) return@KeyboardActions
                            // TODO New Entry process
                            keyboardController?.hide()
                        }
                    )
                }
                DrawDottedLine(pathEffect = Constants.DOT_LINE)
                Button(onClick = {
                    /* TODO - Enregistrer dans DB */
                    Log.d("RECORD", "AddNewKmEntry: ${newKmEntryState.value}")
                    val kmEntry: KmRec = KmRec(
                        id = UUID.randomUUID(),
                        entryDate = Date.from(Instant.now()),
                        days = 0,
                        kmValue = newKmEntryState.value.toDouble(),
                        kmTotal = newKmEntryState.value.toDouble()
                    )
                    try {
    //                    val addRecord = kmRecordViewModel.addRecord(kmEntry)
                    } catch (e: Exception) {
                        Log.d("EXCEPTION", "AddNewKmEntry: $e.message ")
                    }

                    if (validEntryState) {
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "Veuillez entrer une valeur !", Toast.LENGTH_SHORT)
                            .show()
                    }

                }) {
                    Text(text = "Enregistrer")
                }
            }
        }
    }
}