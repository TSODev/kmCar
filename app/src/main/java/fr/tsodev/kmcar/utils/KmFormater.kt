package fr.tsodev.kmcar.utils

import kotlin.math.roundToInt

class KmFormater {



    companion object {
        fun KmToFormatedString( value: Double, length: Int, padChar: Char) : String {
            return value.roundToInt().toString().padStart(length,padChar)
        }
    }
}