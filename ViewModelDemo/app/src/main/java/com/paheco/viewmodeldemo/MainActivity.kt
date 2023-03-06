package com.paheco.viewmodeldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

// https://www.geeksforgeeks.org/viewmodel-in-android-architecture-components/

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // view model instance
        var viewModel: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // setting text view
        var textView = findViewById<TextView>(R.id.textView)
        textView.text = viewModel.number.toString()
        var button = findViewById<Button>(R.id.Mybtn)


        //handling button click event
        button.setOnClickListener {
            viewModel.addOne()
            textView.text = viewModel.number.toString()
        }
    }
}