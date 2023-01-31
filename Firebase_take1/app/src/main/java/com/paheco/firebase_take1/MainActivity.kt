package com.paheco.firebase_take1

import FruitAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val database = Firebase.database
    var fadapter = FruitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchfruits()
    }

    fun fetchfruits() {
        val personRecview = findViewById<RecyclerView>(R.id.fruitRV)
        personRecview.adapter = fadapter
        personRecview.layoutManager = LinearLayoutManager(this)

        loadFruits()

        val fruitSallad = database.getReference("fruitsallad")

        findViewById<Button>(R.id.addFruitBTN).setOnClickListener(){
            var addfruitname = findViewById<EditText>(R.id.addFruitET).text.toString()
            val somefruit = Fruit(addfruitname, "")
            fruitSallad.push().setValue(somefruit)

            loadFruits()
        }
        // Store a fruit
        //val somefruit = Fruit("Äpple", "Röd")
        //fruitSallad.push().setValue(somefruit)
    }

    private fun loadFruits() {
            // Hämta lista med objekt
            val database = Firebase.database
            val fruitSallad = database.getReference("fruitsallad2")
            fruitSallad.get().addOnSuccessListener {
                val fbfruits = mutableListOf<Fruit>()
                it.children.forEach { childsnap ->
                    fbfruits.add(childsnap.getValue<Fruit>()!!)
                }
                /* Loopa genom alla resultat
                for (fruit in fbfruits) {
                    Log.d("pia11debug", fruit.name!!)
                    Log.d("pia11debug", fruit.color!!)
                }
                */
                fadapter.allfruit = fbfruits
                fadapter.notifyDataSetChanged()
            }.addOnFailureListener {
                Log.d("pia11debugLOG", "Push failed")
                println("FAIL!" + it)
            }
        }
    data class User(val username: String? = null, val email: String? = null) {}
    data class Fruit(val name: String? = null, val color: String? = null) {}
}


