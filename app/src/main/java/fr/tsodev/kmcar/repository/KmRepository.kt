package fr.tsodev.kmcar.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class KmRepository(private val firestore: FirebaseFirestore) {

    fun getDocuments(collectionPath: String): Task<QuerySnapshot> {
        return firestore.collection(collectionPath).get()
    }

    fun getDocumentsForUser(collectionPath: String, userId: String) : Task<QuerySnapshot> {
        val collectionRef: CollectionReference = firestore.collection(collectionPath)
        val query: Query = collectionRef.orderBy("date", Query.Direction.ASCENDING)
     //       .whereEqualTo("userId", userId)
        return query.get()
    }

    fun addDocument(collectionPath: String, data: Map<String, Any>): Task<DocumentReference> {
        return firestore.collection(collectionPath).add(data)
    }

    fun updateDocument(documentPath: String, data: Map<String, Any>): Task<Void> {
        return firestore.document(documentPath).update(data)
    }

    fun deleteDocument(documentPath: String): Task<Void> {
        return firestore.document(documentPath).delete()
    }

}
