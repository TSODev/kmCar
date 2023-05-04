package fr.tsodev.kmcar.model


import java.time.Instant
import java.util.Date
import java.util.UUID


data class KmRec(

    val id: UUID = UUID.randomUUID(),

    val entryDate: Date = Date.from(Instant.now()),

    val days: Int,

    val kmValue: Double,

    val kmTotal: Double,


)
