package com.paheco.willitrain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.paheco.willitrain.databinding.ActivityMainBinding
import com.paheco.willitrain.databinding.FragmentSmhiBinding

class MainActivity : AppCompatActivity() {

    var logTag = "willitrainlog"
    var smhiURL = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/12.75277/lat/58.38920/data.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(logTag, "Start")
        setContentView(R.layout.activity_main)
    }
}