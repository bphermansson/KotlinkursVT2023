package com.paheco.willitrain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.paheco.willitrain.databinding.ActivityMainBinding
import com.paheco.willitrain.databinding.FragmentStartBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("willitrainlog", "Start")
        setContentView(R.layout.activity_main)
    }
}