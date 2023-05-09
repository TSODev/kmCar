package fr.tsodev.kmcar.utils

import com.google.firebase.Timestamp
import java.util.Date

fun fromFirestoneTimestamp (date : Any?) : Date {
    val _date = date as Timestamp
    return _date.toDate()
}