package com.paheco.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_start = findViewById<Button>(R.id.btn_start)
        var btn_info = findViewById<Button>(R.id.btn_info)

        btn_start.setOnClickListener(){
            Log.d("FragmentExample", "Start")
            supportFragmentManager.beginTransaction().replace(R.id.mainFragCon, StartFragment()).commit()
            //supportFragmentManager.commit{

            //}
        }
        btn_info.setOnClickListener(){
            Log.d("FragmentExample", "Info")
            supportFragmentManager.beginTransaction().replace(R.id.mainFragCon, InfoFragment()).commit()
        }

    }
}