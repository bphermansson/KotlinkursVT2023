package com.paheco.callbackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.testBTN).setOnClickListener {
            testApi().testCallback() {
                findViewById<TextView>(R.id.testTV).text = it
            }
        }
    }
}