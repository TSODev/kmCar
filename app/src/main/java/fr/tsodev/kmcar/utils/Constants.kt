package fr.tsodev.kmcar.utils

import androidx.compose.ui.graphics.PathEffect
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.utils.DateFormater.Companion.DateToString
import fr.tsodev.kmcar.utils.UUIDConverter.Companion.fromUUID
import java.time.Instant
import java.util.Date
import java.util.UUID

object Constants {
    const val TOTAL_ALLOWED_KM = 100000.0
    val INITIAL_KM_RECORD = KmRec("", "", Date.from(Instant.now()), "0")
    val NO_CAR_FOUND = Car("", "", "","","", false, "0", Date.from(Instant.now()).toString(),Date.from(Instant.now()).toString())
    val DOT_LINE = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
}