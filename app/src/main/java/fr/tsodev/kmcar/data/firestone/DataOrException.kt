package fr.tsodev.kmcar.data.firestone

data class DataOrException<T, Boolean, E: Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null
)
