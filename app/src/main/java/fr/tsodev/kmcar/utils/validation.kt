package fr.tsodev.kmcar.utils

import android.util.Log
import java.util.regex.Pattern

class validation(value: String, pattern: Pattern) {

    companion object {
        fun noValidation(notUsed: String): Boolean = true

        fun isValidEmail(emailStr: String): Boolean {
            var match: Boolean
            val pattern = Regex(
                "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$",
                RegexOption.IGNORE_CASE
            )
            match = pattern.matches(emailStr)
            return match
        }

        fun isAcceptablePassword(password:String): Boolean {

            //Acceptable si 8 caractères minimu avec minuscule ,majuscule et au moins un chiffre

            val pattern = Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$")
            return  pattern.matches(password)
        }

        fun isImmatriculation(plaque: String): Boolean {
            val pattern = Regex("^[A-Z]{2}[-][0-9]{3}[-][A-Z]{2}\$")
            return pattern.matches(plaque)
        }

        fun isAcceptableKmEntry(entry: String): Boolean {

            // entry should be " Value/OldValue" and check if Value > OldValue
            var valid: Boolean = false

            val currentValue: String = entry.substringBefore('/')
            if (!currentValue.isNullOrEmpty()) {
                var value: Int = currentValue.toInt()
                var oldValue: Int = entry.substringAfter('/').toInt()
                valid = (value > oldValue)
            }

            return valid
        }
    }
}