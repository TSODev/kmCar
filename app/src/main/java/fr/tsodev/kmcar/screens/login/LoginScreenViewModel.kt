package fr.tsodev.kmcar.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import fr.tsodev.kmcar.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {

    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    fun createUserWithEmailAndPassword(email: String, password: String, callback: () -> Unit) {
        val car = Constants.NO_CAR_FOUND
        if (_loading.value == false) _loading.value = true
    try {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val displayName = task.result.user?.email?.split('@')?.get(0)
                    createUser(displayName)
                    callback()
                } else {
                    Log.d("FBAUTH", "createUserWithEmailAndPassword: ${task.exception?.message}")
                }
                _loading.value = false
            }
    } catch (e : Exception) {
        Log.d("FBAUTH", "createUserWithEmailAndPassword: ${e.message}", )
    }
    }

    private fun createUser(displayName: String?) {
        val currentUser = auth.currentUser
        val userId = auth.currentUser?.uid
        val user = mutableMapOf<String, Any>()
        user["user_id"] = userId.toString()
        user["display_name"] = displayName.toString()

//        val vehicule: MutableMap<String, Any> = mutableMapOf<String, Any>()
//        val carId = UUID.randomUUID().toString()
//        vehicule["id"] = carId
//        vehicule["userId"] = userId.toString()
//        vehicule["plaque"] = car.toString()

//        user["carId"] = carId.toString()

//        FirebaseFirestore.getInstance().collection("cars").add(vehicule)
//            .addOnSuccessListener {
//                Log.d("FBSTORE", "onCreate: ${it.id}")
//            }
//            .addOnFailureListener {
//                Log.d("FBSTORE", "onCreate: (Failure) $it, for ${currentUser.toString()}")
//            }



        FirebaseFirestore.getInstance().collection("users").add(user)
            .addOnSuccessListener {
                Log.d("FBSTORE", "onCreate: ${it.id}")
            }
            .addOnFailureListener {
                Log.d("FBSTORE", "onCreate: (Failure) $it, for ${currentUser.toString()}")
            }


    }

    fun signInWithUserAndPassword(email: String, password: String,onError: () -> Unit,  onSuccess: () -> Unit)
            = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Log.d("FBAUTH", "createUserWithEmailAndPassword: ${task.result.toString()}")
                        onSuccess()
                    } else {
                        onError()
                        Log.d("FBAUTH", "createUserWithEmailAndPassword: ${task.exception?.message}")
                    }

                }
        } catch (e: Exception) {
            Log.d("FBAUTH", "createUserWithEmailAndPassword: ${e.message}", )
        }

    }
}