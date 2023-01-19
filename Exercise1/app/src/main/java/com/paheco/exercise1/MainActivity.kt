package com.paheco.exercise1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtRubrik = findViewById<TextView>(R.id.txtRubrik)
        val btnKiwi = findViewById<Button>(R.id.btnKiwi)

        findViewById<Button>(R.id.btnBanan).setOnClickListener {
            txtRubrik.text = resources.getString(R.string.banan)
            btnKiwi.visibility = View.VISIBLE
        }
        findViewById<Button>(R.id.btnApelsin).setOnClickListener {
            txtRubrik.text = resources.getString(R.string.apelsin)
            btnKiwi.visibility = View.INVISIBLE
        }
        findViewById<Button>(R.id.btnKiwi).setOnClickListener {
            txtRubrik.text = resources.getString(R.string.kiwi)
        }
    }
}