package fr.tsodev.kmcar.utils

import kotlin.math.roundToInt

class KmFormater {



    companion object {
        fun KmToFormatedString( value: String, length: Int, padChar: Char) : String {
            return value.padStart(length,padChar)
        }
    }
}