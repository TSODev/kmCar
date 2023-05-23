package fr.tsodev.kmcar.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import fr.tsodev.kmcar.model.Car
import fr.tsodev.kmcar.model.KmRec
import fr.tsodev.kmcar.utils.Constants

class KmRepository(private val firestore: FirebaseFirestore) {

    val TAG = "KMREPOSITORY"

    fun getDocuments(collectionPath: String): Task<QuerySnapshot> {
        return firestore.collection(collectionPath).get()
    }

    fun getDocumentsForUser(collectionPath: String, userId: String) : Task<QuerySnapshot> {
        val collectionRef: CollectionReference = firestore.collection(collectionPath)
        val query: Query = collectionRef.orderBy("date", Query.Direction.ASCENDING)
     //       .whereEqualTo("userId", userId)
        return query.get()
    }

    fun getDocumentsForCar(carId: String) : Task<QuerySnapshot> {
        val collectionRef: CollectionReference = firestore.collection("cars")
        val query: Query = collectionRef.whereEqualTo("id", carId)
        //       .whereEqualTo("userId", userId)
        return query.get()
    }

    fun getEntriesForCar(carId: String) : Task<QuerySnapshot> {
        val collectionRef: CollectionReference = firestore.collection("kmEntries")
        val query: Query = collectionRef.whereEqualTo("carId", carId)
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

    fun deleteCar(carId: String): Task<Unit>{
        val collectionCarRef: CollectionReference = firestore.collection("cars")
        val queryCar: Query = collectionCarRef.whereEqualTo("id", carId)

        val resultCar = queryCar.get().continueWith { querySnapshot ->
            querySnapshot.result.forEach { docCar ->
                docCar.reference.delete()
            }
        }
        return resultCar
    }

    fun deleteCarAndRelatedEntries(carId: String): Task<Unit>{
        val collectionCarRef: CollectionReference = firestore.collection("cars")
        val queryCar: Query = collectionCarRef.whereEqualTo("id", carId)
        val collectionKmEntriesRef: CollectionReference = firestore.collection("kmEntries")
        val queryKm: Query = collectionKmEntriesRef.whereEqualTo("carId", carId)
        val resultKm = queryKm.get()
            .addOnSuccessListener {
                it.documents.forEach { docKm ->
                    docKm.reference.delete()
                    Log.d(TAG, "deleteKmForCar: KmEntry ${docKm.toObject(KmRec::class.java)}")
                }
            }

        val resultCar = queryCar.get().continueWith { querySnapshot ->
            querySnapshot.result.forEach { docCar ->
                docCar.reference.delete()
                Log.d(TAG, "deleteKmForCar: Car ${docCar.toObject(Car::class.java)}")
            }
        }
        return resultCar
    }

    fun deleteKmForCar(carId: String): Task<QuerySnapshot>{
        val collectionKmEntriesRef: CollectionReference = firestore.collection("kmEntries")
        val queryKm: Query = collectionKmEntriesRef.whereEqualTo("carId", carId)

        val resultKm = queryKm.get()
            .addOnSuccessListener {
                it.documents.forEach { docKm ->
                        docKm.reference.delete()
                    Log.d(TAG, "deleteKmForCar: ${docKm.toObject(KmRec::class.java)}")
            }
        }

        return resultKm
    }

    fun importCar(carId: String): Task<Unit> {
        var car: Car = Constants.NO_CAR_FOUND
        val collectionCarRef: CollectionReference = firestore.collection("cars")
        val queryCar: Query = collectionCarRef.whereEqualTo("id", carId)

        val resultCar = queryCar.get()
            .continueWith() { querySnapshot ->
                querySnapshot.result.forEach() { docCar ->
                    car = docCar.toObject(Car::class.java)!!
                    val currentUser = FirebaseAuth.getInstance().uid
                    if (currentUser != null) {
                        car.userId = currentUser
                    }
                    addDocument("cars", car.toMap())
                }
            }
        return resultCar
    }

}
