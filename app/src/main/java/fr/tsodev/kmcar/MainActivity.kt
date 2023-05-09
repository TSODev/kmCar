package fr.tsodev.kmcar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import fr.tsodev.kmcar.navigation.KmCarNavigation
import fr.tsodev.kmcar.ui.theme.KmCarTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KmCarTheme {
                KmCarNavigation()
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KmCarTheme {
        KmCarNavigation()
    }
}