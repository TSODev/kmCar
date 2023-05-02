package fr.tsodev.kmcar.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import java.util.UUID

@Entity(tableName = "kmRecords_tbl")
data class kmRecord(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "km_date")
    val entryDate: Date = Date.from(Instant.now()),

    @ColumnInfo(name="nb_days")
    val days: Int,

    @ColumnInfo(name = "km_value")
    val kmValue: Double,

    @ColumnInfo(name= "km_total")
    val kmTotal: Double,


)
