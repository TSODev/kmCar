package fr.tsodev.kmcar.screens

import android.annotation.SuppressLint
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import fr.tsodev.kmcar.R
import fr.tsodev.kmcar.components.KmLogo
import fr.tsodev.kmcar.navigation.KmCarNavScreens
import kotlinx.coroutines.delay

@SuppressLint("RememberReturnType")
@Composable
fun SplashScreen(navController : NavController) {

    val scale = remember {
            Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 0.9f,
                        animationSpec = tween( durationMillis = 800,
                                        easing = {
                                            OvershootInterpolator().getInterpolation(it)
                                        }))
        delay(1500L)
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty())
            navController.navigate(KmCarNavScreens.KmCarLoginScreen.name)
        else
            navController.navigate(KmCarNavScreens.HomeScreen.name)
    }


    Card(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp)
        .height(330.dp)
        .scale(scale.value),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant,),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
            ) {
            Column( modifier = Modifier.padding(1.dp)
                .background(MaterialTheme.colorScheme.onSecondary)
                .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

            )
                {
                    Text(text = "KmCar",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary)
 //                   KmLogo(imageId = R.drawable.car)
                    Spacer( modifier = Modifier.height(16.dp))
                    Text(text = " Suivi de votre LOA", style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary)
            }

    }
}

