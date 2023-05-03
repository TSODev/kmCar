package fr.tsodev.kmcar.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import fr.tsodev.kmcar.util.KmFormater
import fr.tsodev.kmcar.util.KmFormater.Companion.KmToFormatedString
import java.text.DecimalFormat
import kotlin.math.roundToInt

@Composable
fun CompteurKm( value : Double) {

    val displayedKM: String = KmToFormatedString(value, 6, '0')

    Text(text = "$displayedKM Kms",
        //
        style = TextStyle(
            color = Color.Black,
            fontSize = 38.sp,
            fontWeight = FontWeight.ExtraBold
        ),
        textAlign = TextAlign.End
    )
}