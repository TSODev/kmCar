package fr.tsodev.kmcar.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PasswordInputField(
    modifier: Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    imageVector: ImageVector,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions
) {

    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()

    OutlinedTextField(value = valueState.value,
        onValueChange = {valueState.value = it},
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
        modifier = modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
        leadingIcon = { Icon(imageVector = imageVector, contentDescription = "Icon") },
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
            imeAction = imeAction),
        keyboardActions = onAction,
        visualTransformation = visualTransformation,
        trailingIcon = {PasswordVisibility(passwordVisibility = passwordVisibility)}
    )
}


@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible}) {
        Icons.Default.Close
    }
}
