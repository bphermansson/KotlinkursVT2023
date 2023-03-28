package com.paheco.willitrain

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.RECORD_AUDIO

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.paheco.willitrain.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        var logTag = "willitrainlog"
        var smhiURL =
            "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/12.75277/lat/58.38920/data.json"
        var smhiURLP1 =
            "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/"
        var smhiLon = "12.75277"
        var smhiLatTxt = "/lat/"
        var smhiLat = "58.38920"
        var smhiUrlEnding = "/data.json"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var viewModel: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        Log.i(logTag, "Start")
        setContentView(R.layout.activity_main)


        // TODO: Add a menu with Settings(position)
        // TODO: Use a map to select location

        // TODO: Or, why not just use the position?
        //Snackbar.make(findViewById(android.R.id.content), "Välkommen!", Snackbar.LENGTH_LONG).show();
    }
}