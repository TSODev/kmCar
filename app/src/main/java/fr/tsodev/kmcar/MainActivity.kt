package fr.tsodev.kmcar

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import fr.tsodev.kmcar.navigation.KmCarNavigation
import fr.tsodev.kmcar.screen.KMRecordViewModel
import fr.tsodev.kmcar.ui.theme.KmCarTheme
import fr.tsodev.kmcar.util.Constants

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val kmRecordViewModel: KMRecordViewModel by viewModels()
    companion object {
        var lastKmRec = Constants.INITIAL_KM_RECORD
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Read Room DB
        try {
            lastKmRec = kmRecordViewModel.kmRecordList.value.last()
        } catch (e: Exception) {
            e.message?.let { Log.d("EXCEPTION", it) }
        }

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