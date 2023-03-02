package com.paheco.api

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ChuckAPI().loadJoke() {
            runOnUiThread {
                findViewById<TextView>(R.id.jokeTV).text = it.value
            }
        }
        findViewById<Button>(R.id.newjokeBtn).setOnClickListener{
            ChuckAPI().loadJoke() {
                runOnUiThread {
                    findViewById<TextView>(R.id.jokeTV).text = it.value
                }
            }
        }
    }
}
