package com.paheco.willitrain
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    companion object {
        var logTag = "willitrainlog"
        //var smhiURL = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/12.75277/lat/58.38920/data.json"
        var smhiURLP1 =
            "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/"
        var smhiLon = "12.75277"
        var smhiLatTxt = "/lat/"
        var smhiLat = "58.38920"
        var smhiUrlEnding = "/data.json"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}