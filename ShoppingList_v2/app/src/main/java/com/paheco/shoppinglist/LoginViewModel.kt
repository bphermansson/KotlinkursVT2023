package com.paheco.shoppinglist

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {

    var errorMessage = mutableLiveData<String>()
    {
        MutableLiveData<String>
    }

    fun login(email:String, password: String)
    {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(){task ->
                if (task.isSuccessful) {
                    //Toast.makeText(requireContext(), "Register succeeded.", Toast.LENGTH_SHORT).show()
                } else {
                    //Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun register(email:String, password:String){
        Firebase.auth.createUserWithEmailAndPassword(email : String, password : String)
            .addOnCompleteListener()task ->

        //Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener()task ->
                if (task.isSuccessful) {
                    //Toast.makeText(requireContext(), "Register succeeded.", Toast.LENGTH_SHORT).show()
                } else {
                    //Toast.makeText(requireContext(), "Register failed", Toast.LENGTH_SHORT).show()
                    errorMessage.value = "Fel registrering"
                    Log.i("debug", task.exception!!.message)
                    Log.i("debug", task.exception!!.localizedMessage)
                }
            }
    }


}