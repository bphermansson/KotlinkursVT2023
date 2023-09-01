package com.example.pia11mndag16jan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var personadapter = PersonAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personadapter.people.add("Erik")
        personadapter.people.add("Fredrik")
        Log.i("pia11debug", "HEJ")

        val personRecView = findViewById<RecyclerView>(R.id.personsRecyclerview)
        personRecView.adapter = personadapter
        personRecView.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.addPersonButton).setOnClickListener {
            var addname = findViewById<EditText>(R.id.addpersonET).text.toString()
            Log.i("pia11debug", addname)
            personadapter.people.add(addname)
            personadapter.notifyDataSetChanged()
        }
    }

}