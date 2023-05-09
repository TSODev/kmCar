package fr.tsodev.kmcar.screens.login

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.auth.User
import fr.tsodev.kmcar.components.KmLogo
import fr.tsodev.kmcar.components.UserForm
import fr.tsodev.kmcar.navigation.KmCarNavScreens

@Composable
fun KmCarLoginScreen (
    navController: NavController,
    viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    ) {

    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "KmCar",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(100.dp))
            if (showLoginForm.value)
                UserForm(loading = false, isCreateAccount = false) { email, password ->
                    viewModel.signInWithUserAndPassword(email = email, password = password)
                    {
                        navController.navigate(KmCarNavScreens.HomeScreen.name)
                    }
                }
            else UserForm(loading = false, isCreateAccount = true) { email, password ->
                viewModel.createUserWithEmailAndPassword(email = email, password = password)
                {
                    navController.navigate(KmCarNavScreens.HomeScreen.name)
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            val textbutton = if (showLoginForm.value) "Créer un Compte" else "Login"
            val textuser =
                if (showLoginForm.value) "Nouvel utilisateur ? " else "Utilisateur enregistré ? "
            Text(text = textuser)
            Text(text = textbutton, modifier = Modifier
                .clickable {
                    showLoginForm.value = !showLoginForm.value
                }
                .padding(5.dp),
                fontWeight = Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

fun navigateByReturnStatus(success: Boolean, navController: NavController) {
    if (success) {
        navController.navigate(KmCarNavScreens.HomeScreen.name)
    } else {
        navController.navigate(KmCarNavScreens.KmCarLoginScreen.name)
    }
}
