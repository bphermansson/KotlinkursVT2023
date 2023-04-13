package com.paheco.willitrain
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.paheco.willitrain.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        var logTag = "willitrainlog"
        var smhiURLP1 = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/"
        var smhiLon = "12.75277"
        var smhiLatTxt = "/lat/"
        var smhiLat = "58.38920"
        var smhiUrlEnding = "/data.json"
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}