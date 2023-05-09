package fr.tsodev.kmcar.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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

    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }

    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    val modifier = Modifier
        .padding(20.dp)
        .height(300.dp)
        .background(MaterialTheme.colorScheme.background)
        .verticalScroll(rememberScrollState())

    Column(modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally) {

                if (isCreateAccount) Text(text = stringResource(id = R.string.create_account),
                modifier = Modifier.padding(6.dp)) else Text("")

                InputField(valueState = email, labelId = "Email", enabled = !loading, isSingleLine = true,
                imageVector = Icons.Rounded.Email, keyboardType= KeyboardType.Email,
                onAction = KeyboardActions {
                    passwordFocusRequest.requestFocus()
                })

                PasswordInputField(modifier = Modifier.focusRequester(passwordFocusRequest),
                    valueState = password,
                    labelId = "Password",
                    enabled = !loading,
                    isSingleLine = true,
                    imageVector = Icons.Rounded.Lock,
                    passwordVisibility = passwordVisibility,
                    onAction = KeyboardActions{
                        if (!valid) return@KeyboardActions
                        onDone(email.value.trim(), password.value.trim())
                    })

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


