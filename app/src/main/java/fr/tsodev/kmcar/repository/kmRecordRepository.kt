package fr.tsodev.kmcar.repository

import fr.tsodev.kmcar.data.kmRecordDBDao
import fr.tsodev.kmcar.model.KmRec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class kmRecordRepository @Inject constructor(private val kmRecordDBDao: kmRecordDBDao) {
    suspend fun addRecord(kmRec: KmRec) = kmRecordDBDao.insert(kmRec)
    suspend fun updateRecord(kmRec: KmRec) = kmRecordDBDao.update(kmRec)
    suspend fun deleteRecord(kmRec: KmRec) = kmRecordDBDao.deleteRecord(kmRec)
    suspend fun deleteAllRecord() = kmRecordDBDao.deleteAll()
    fun getAllRecords(): Flow<List<KmRec>> = kmRecordDBDao.getRecords().flowOn(
        Dispatchers.IO).conflate()
}