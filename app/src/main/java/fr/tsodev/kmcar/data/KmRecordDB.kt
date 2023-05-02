package fr.tsodev.kmcar.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.tsodev.kmcar.model.kmRecord
import fr.tsodev.kmcar.util.DateConverter
import fr.tsodev.kmcar.util.UUIDConverter

@Database(entities = [kmRecord::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class KmRecordDB : RoomDatabase() {
    abstract fun kmRecordDao(): kmRecordDBDao
}