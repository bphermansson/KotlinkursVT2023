package com.paheco.willitrain
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar

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

        val toolbar = findViewById(R.id.toolbar) as Toolbar?
                toolbar?.title = "Androidly"
                toolbar?.setNavigationOnClickListener {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, InfoFragment()).commit()
                }
    }
}