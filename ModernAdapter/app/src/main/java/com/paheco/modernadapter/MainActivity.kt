package com.paheco.modernadapter

import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity:AppCompatActivity(){

    private lateinit var data:ArrayList<ColorSpace.Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        data = arrayListOf()
    }

    // Initializing the adapter
    val adapter = ModernAdapter(data) {model ->
        /** Handling the click **/
    }

}