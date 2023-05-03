package fr.tsodev.kmcar.util

import androidx.compose.ui.graphics.PathEffect
import fr.tsodev.kmcar.model.KmRec
import java.time.Instant
import java.util.Date
import java.util.UUID

object Constants {
    const val TOTAL_ALLOWED_KM = 75000
    val INITIAL_KM_RECORD = KmRec(UUID.randomUUID(), Date.from(Instant.now()), 1, 0.0, 0.0)
    val DOT_LINE = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
}