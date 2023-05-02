package fr.tsodev.kmcar.repository

import fr.tsodev.kmcar.data.kmRecordDBDao
import fr.tsodev.kmcar.model.kmRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class kmRecordRepository @Inject constructor(private val kmRecordDBDao: kmRecordDBDao) {
    suspend fun addRecord(kmRecord: kmRecord) = kmRecordDBDao.insert(kmRecord)
    suspend fun updateRecord(kmRecord: kmRecord) = kmRecordDBDao.update(kmRecord)
    suspend fun deleteRecord(kmRecord: kmRecord) = kmRecordDBDao.deleteRecord(kmRecord)
    suspend fun deleteAllRecord() = kmRecordDBDao.deleteAll()
    fun getAllRecords(): Flow<List<kmRecord>> = kmRecordDBDao.getRecords().flowOn(
        Dispatchers.IO).conflate()
}