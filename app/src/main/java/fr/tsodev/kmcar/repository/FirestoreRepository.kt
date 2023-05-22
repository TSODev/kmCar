package fr.tsodev.kmcar.repository

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import fr.tsodev.kmcar.data.firestore.DataOrException
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.model.KmRec
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepository @Inject constructor(private val queryEntry: Query,
                                                private val queryCar: Query): ViewModel() {

//    val firestore = FirebaseFirestore.getInstance()

    suspend fun getAllEntriesFromDatabase(): DataOrException<List<KmRec>, Boolean, Exception> {
        val dataOrException = DataOrException<List<KmRec>, Boolean, Exception>()
        try {
            dataOrException.loading = true
            dataOrException.data = queryEntry.get().await().documents.map { documentSnapshot: DocumentSnapshot ->
                documentSnapshot.toObject(KmRec::class.java)!!
            }
            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false
        } catch(exception: FirebaseFirestoreException) {
            dataOrException.e = exception
        }
        return dataOrException
    }

    suspend fun getAllCarsFromDatabase(): DataOrException<List<Car>, Boolean, Exception> {
        val dataOrException = DataOrException<List<Car>, Boolean, Exception>()
        try {
            dataOrException.loading = true
            dataOrException.data = queryCar.get().await().documents.map { documentSnapshot: DocumentSnapshot ->
                documentSnapshot.toObject(Car::class.java)!!
            }
//            if (!dataOrException.data.isNullOrEmpty())
                dataOrException.loading = false
        } catch(exception: FirebaseFirestoreException) {
            dataOrException.e = exception
        }
        return dataOrException
    }
}