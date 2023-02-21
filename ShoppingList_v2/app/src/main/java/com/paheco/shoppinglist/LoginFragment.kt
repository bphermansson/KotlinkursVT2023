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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.paheco.shoppinglist.databinding.FragmentLoginBinding
import com.paheco.shoppinglist.databinding.FragmentShoppingBinding

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    val model by viewModels<LoginViewModel>()

    var _binding : FragmentLoginBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        val errorObserver = Observer<String> {errorMess ->
            Toast.makeText(requireContext(), errorMess, Toast.LENGTH_LONG).show()
        }
        model.errorMessage.observe(viewLifecycleOwner, errorObserver)

        binding.loginBT.setOnClickListener{
            val userEmail = binding.loginEmailET.text.toString()
            val userPassword = binding.loginPasswordET.text.toString()

            if(userEmail=="") {
                model.errorMessage.value = "Fyll i lösenord!"
            }
            auth.signInWithEmailAndPassword (userEmail, userPassword)
                .addOnCompleteListener(requireActivity()) { task ->
                    model.login(userEmail, userPassword)
                }
        }

        binding.registerBT.setOnClickListener{
            val userEmail = binding.loginEmailET.text.toString()
            val userPassword = binding.loginPasswordET.text.toString()
            model.register(userEmail, userPassword)
        }
    }

}