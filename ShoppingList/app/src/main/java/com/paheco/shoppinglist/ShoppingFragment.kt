package com.paheco.shoppinglist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ShoppingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.logoutBT).setOnClickListener{
          Firebase.auth.signOut()
        }
        view.findViewById<Button>(R.id.itemAddBTN).setOnClickListener{
            val addItem = view.findViewById<EditText>(R.id.itemET).text.toString()
            var tempItem: ShoppingItem
            tempItem = ShoppingItem(addItem)

            val database = Firebase.database
            val shopRef = database.getReference("ShoppingList").child(Firebase.auth.currentUser!!.uid).push()
            shopRef.push().setValue(tempItem)

        }
    }
}