package fr.tsodev.kmcar.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.tsodev.kmcar.model.KmRec
import kotlinx.coroutines.flow.Flow

@Dao
interface kmRecordDBDao {

    @Query("SELECT * from kmRecords_tbl")
    fun getRecords(): Flow<List<KmRec>>

    @Query("SELECT * from kmRecords_tbl where id =:id")
    suspend fun getRecordById(id: String): KmRec

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(kmRec: KmRec)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(kmRec: KmRec)

    @Query("DELETE from kmRecords_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteRecord(kmRec: KmRec)
}
