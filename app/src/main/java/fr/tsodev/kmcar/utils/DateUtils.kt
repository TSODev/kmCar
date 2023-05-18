package fr.tsodev.kmcar.utils

import java.text.DateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class DateUtils {

    companion object {

        fun dateToString(date: Date) : String {
            val df = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE)
            return df.format(date)

        }

        fun convertLocalDateToString(localDate: LocalDate, format: String): String {
            val formatter = DateTimeFormatter.ofPattern(format)
            return localDate.format(formatter)
        }

        fun convertFrenchLocalDateToString(localDate: LocalDate, format: String): String {
            val formatter = DateTimeFormatter.ofPattern(format, Locale.FRANCE)
            return localDate.format(formatter)
        }

        fun dateFromString(dateString: String): LocalDate {
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            return LocalDate.parse(dateString, formatter)
        }

        fun convertStringToLocalDate(dateString: String, format: String): LocalDate {
            val formatter = DateTimeFormatter.ofPattern(format)
            return LocalDate.parse(dateString, formatter)
        }

        fun convertStringToFrenchLocalDate(dateString: String, format: String): LocalDate {
            val formatter = DateTimeFormatter.ofPattern(format, Locale.FRANCE)
            return LocalDate.parse(dateString, formatter)
        }

        private fun dateDiffInDays(dateFrom: Date, dateTo: Date): Long {
            val diff: Long = dateTo.time - dateFrom.time
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
        }

        fun calculateDateDifference(start: LocalDate, end: LocalDate): Long {
            return ChronoUnit.DAYS.between(start, end)
        }

        fun convertDateToLocalDate(date: Date): LocalDate {
            val instant = date.toInstant()
            return instant.atZone(ZoneId.systemDefault()).toLocalDate()
        }

        fun nbOfDayBetweenTwoDates(dateFrom: Date, dateTo: Date): Int {
            return Math.toIntExact(dateDiffInDays(dateFrom = dateFrom, dateTo = dateTo))
        }

        fun convertLocalDateToDate(localDate: LocalDate): Date {
            val zoneId = ZoneId.systemDefault()
            val instant = localDate.atStartOfDay(zoneId).toInstant()
            return Date.from(instant)
        }

        fun convertDaysToLocalDate(days: Long): LocalDate {
            val startDate = LocalDate.of(1970, 1, 1) // Starting date (adjust as needed)
            return startDate.plusDays(days)
        }

    }
}