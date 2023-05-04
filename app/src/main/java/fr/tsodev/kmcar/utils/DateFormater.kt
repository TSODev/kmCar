package fr.tsodev.kmcar.utils

import java.text.DateFormat
import java.util.Date
import java.util.Locale

class DateFormater {

    companion object {
        fun DateToString(date: Date) : String {
            val df = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE)
            return df.format(date)

        }
    }


}