package fr.tsodev.kmcar.repository

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import fr.tsodev.kmcar.data.firestone.DataOrException
import fr.tsodev.kmcar.model.KmRec
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepository @Inject constructor(private val queryEntry: Query): ViewModel() {

    val firestore = FirebaseFirestore.getInstance()

    suspend fun getAllEntriesFromDatabase(): DataOrException<List<KmRec>, Boolean, Exception> {
        val dataOrException = DataOrException<List<KmRec>, Boolean, Exception>()
        try {
            dataOrException.loading = true
            dataOrException.data = queryEntry.get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(KmRec::class.java)!!
            }
            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false
        } catch(exception: FirebaseFirestoreException) {
            dataOrException.e = exception
        }
        return dataOrException
    }
}