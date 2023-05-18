package fr.tsodev.kmcar.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import fr.tsodev.kmcar.R

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm (
    loading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = {email, pwd ->},
) {
    val email = rememberSaveable {
            mutableStateOf("")
    }

    val password = rememberSaveable {
        mutableStateOf("")
    }

    val car = rememberSaveable { mutableStateOf("")}

    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }

    val passwordFocusRequest = FocusRequester.Default
    val carFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    val modifier = Modifier
        .padding(20.dp)
        .height(500.dp)
        .background(MaterialTheme.colorScheme.background)
        .verticalScroll(rememberScrollState())

    Column(modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally) {

                if (isCreateAccount) 
                    Text(
                        text = stringResource(id = R.string.create_account),
                        modifier = Modifier.padding(6.dp)
                    ) else 
                        Text("")

                Spacer(modifier = Modifier.height(80.dp))
                InputField(
                    valueState = email, 
                    labelId = "Adresse Email", 
                    enabled = !loading, 
                    isSingleLine = true,
                imageVector = Icons.Rounded.Email, 
                    keyboardType= KeyboardType.Email,
                onAction = KeyboardActions {
                    passwordFocusRequest.requestFocus()
                },
                visible = true)

                PasswordInputField(modifier = Modifier.focusRequester(passwordFocusRequest),
                    valueState = password,
                    labelId = "Mot de Passe",
                    enabled = !loading,
                    isSingleLine = true,
                    imageVector = Icons.Rounded.Lock,
                    passwordVisibility = passwordVisibility,
                    onAction = KeyboardActions{
                        if (!valid) return@KeyboardActions
                        onDone(email.value.trim(), password.value.trim())
//                            carFocusRequest.requestFocus()
                    })

            Spacer(modifier = Modifier.height(160.dp))
        
            SubmitButton(
                textId = if (isCreateAccount) "Créer un compte" else "Login",
                loading = loading,
                validInputs = valid,
            ){
                onDone(email.value.trim(), password.value.trim())
                keyboardController?.hide()
            }
    }
}


