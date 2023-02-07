package com.paheco.exercise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContentItem(val name: String, val imageUrl: String)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun fragmentMethod() {
        Toast.makeText(this@MainActivity, "Method called From Fragment",
            Toast.LENGTH_LONG).show()
    }
}