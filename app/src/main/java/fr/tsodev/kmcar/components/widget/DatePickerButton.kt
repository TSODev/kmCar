package fr.tsodev.kmcar.components.widget

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerButton(
    modifier : Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    imageVector : ImageVector = Icons.Rounded.LocationOn,
    enabled: Boolean,
//    onDone: () -> Unit,
    visible: Boolean
) {
    val showDialog: MutableState<Boolean> = remember {mutableStateOf(false)}
    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.FRANCE )

    if (visible) {
        Button(
            onClick = { showDialog.value = true },
            modifier = modifier
                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                .height(60.dp)
                .width(350.dp),

            enabled = enabled,
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, Color.DarkGray)
            ) {
            Row(
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(imageVector = imageVector, contentDescription = "Icon", tint = Color.Black)
                Spacer(modifier = Modifier.width(14.dp))

                if (valueState.value.isNullOrEmpty()) {
                    Text(
                        text = labelId,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                    )
                } else {
                    Text(
                        text = valueState.value,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }

            if (showDialog.value) {
            val datePickerState = rememberDatePickerState()
            val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
            DatePickerDialog(
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onDismissRequest.
                    showDialog.value = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog.value = false
                            valueState.value = sdf.format(datePickerState.selectedDateMillis).toString()
                        },
                        enabled = confirmEnabled.value
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialog.value = false
                        }
                    ) {
                        Text("Annule")
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.padding(5.dp),
                    title = { Text(text = "Choisir une date") },
 //                   headline = { Text(text = labelId) },
 //                   showModeToggle = false,

                )
            }
        }

        }
    }
    else
        Box {

    }

}
