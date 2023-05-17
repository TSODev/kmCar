package fr.tsodev.kmcar.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SubmitButton(textId: String,
                 loading: Boolean = false,
                 validInputs: Boolean = true,
                 onClick: () -> Unit) {

    Button(onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxSize(),
        enabled = !loading && validInputs,
        shape = CircleShape
    ){
        if (loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
        else Text(text = textId, modifier = Modifier.padding(5.dp))
    }
}