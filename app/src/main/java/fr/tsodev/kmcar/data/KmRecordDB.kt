package fr.tsodev.kmcar.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.util.DateConverter
import fr.tsodev.kmcar.util.UUIDConverter

@Database(entities = [KmRec::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class KmRecordDB : RoomDatabase() {
    abstract fun kmRecordDao(): kmRecordDBDao
}