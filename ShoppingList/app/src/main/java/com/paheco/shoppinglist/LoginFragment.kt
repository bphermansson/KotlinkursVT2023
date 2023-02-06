package com.paheco.shoppinglist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        view.findViewById<Button>(R.id.loginBT).setOnClickListener{
            val userEmail = view.findViewById<EditText>(R.id.loginEmailET).text.toString()
            val userPassword = view.findViewById<EditText>(R.id.loginPasswordET).text.toString()

            //auth.createUserWithEmailAndPassword(userEmail, userPassword)
            auth.signInWithEmailAndPassword (userEmail, userPassword)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // The toast will fail as we switched context before showing the toast.
                        //Toast.makeText(requireContext(), "Login succeeded.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        view.findViewById<Button>(R.id.registerBT).setOnClickListener{

            val userEmail = view.findViewById<EditText>(R.id.loginEmailET).text.toString()
            val userPassword = view.findViewById<EditText>(R.id.loginPasswordET).text.toString()

            auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Register succeeded.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Register failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}