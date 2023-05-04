package fr.tsodev.kmcar.data.firestone

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase

class KmDB()  {

        // [START get_firestore_instance]
        private val db = Firebase.firestore

        private fun setup() {
                // [START get_firestore_instance]
                val db = Firebase.firestore
                // [END get_firestore_instance]

                // [START set_firestore_settings]
                val settings = firestoreSettings {
                        isPersistenceEnabled = true
                }
                db.firestoreSettings = settings
                // [END set_firestore_settings]
        }

        private fun setupCacheSize() {
                // [START fs_setup_cache]
                val settings = firestoreSettings {
                        cacheSizeBytes = FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED
                }
                db.firestoreSettings = settings
                // [END fs_setup_cache]
        }

        fun getDBInstance(): FirebaseFirestore {
                return db
        }
}