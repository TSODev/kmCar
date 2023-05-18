package fr.tsodev.kmcar.utils

import androidx.compose.ui.graphics.PathEffect
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.model.KmRec
import java.time.Instant
import java.util.Date

object Constants {
    val NO_CAR_FOUND = Car("", "", "","","", false, "0", Date.from(Instant.now()).toString(),Date.from(Instant.now()).toString())
    val DOT_LINE = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
}