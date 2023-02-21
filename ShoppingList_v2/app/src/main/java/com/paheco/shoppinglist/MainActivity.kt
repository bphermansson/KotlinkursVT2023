package com.paheco.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import se.magictechnology.pia11shopping.ShoppingFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(Firebase.auth.currentUser != null) {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainerView, ShoppingFragment())
                findViewById<FragmentContainerView>(R.id.fragmentContainerView).visibility = View.VISIBLE
            }
        }
        else {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainerView, LoginFragment())
            }
        }

        Firebase.auth.addAuthStateListener {
            if(Firebase.auth.currentUser != null) {
                supportFragmentManager.commit {
                    replace(R.id.fragmentContainerView, ShoppingFragment())
                }
            }
            else {
                supportFragmentManager.commit {
                    replace(R.id.fragmentContainerView, LoginFragment())
                }
            }
        }

    }
}