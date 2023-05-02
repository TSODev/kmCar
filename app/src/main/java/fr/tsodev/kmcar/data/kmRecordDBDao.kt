package fr.tsodev.kmcar.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.tsodev.kmcar.model.kmRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface kmRecordDBDao {

    @Query("SELECT * from kmRecords_tbl")
    fun getRecords(): Flow<List<kmRecord>>

    @Query("SELECT * from kmRecords_tbl where id =:id")
    suspend fun getRecordById(id: String): kmRecord

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(kmRecord: kmRecord)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(kmRecord: kmRecord)

    @Query("DELETE from kmRecords_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteRecord(kmRecord: kmRecord)
}
