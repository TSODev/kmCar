package fr.tsodev.kmcar.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField (
    modifier : Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    imageVector : ImageVector = Icons.Rounded.LocationOn,
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType : KeyboardType = KeyboardType.Ascii,
    imeAction : ImeAction = ImeAction.Next,
    onAction : KeyboardActions = KeyboardActions.Default,
    validationRegEx: String = "",
    visible: Boolean
    ) {
        if (visible)
        OutlinedTextField(value = valueState.value,
            onValueChange = {valueState.value = it},
            label = { Text(text = labelId)},
            singleLine = isSingleLine,
            textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimaryContainer),
            modifier = modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
            leadingIcon = { Icon(imageVector = imageVector, contentDescription = "Icon")},
            enabled = enabled,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType,
                                        imeAction = imeAction),
            keyboardActions = onAction
        )
        else
            Box() {

            }
}