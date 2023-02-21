package com.paheco.databinding_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.paheco.databinding_livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //val model : MainViewModel by viewModels()
    val model by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.fancytext = "Bananer!"

        val nameObserver = Observer<String>{ newName ->
            findViewById<TextView>(R.id.mainTV).text = newName
        }
        model.currentName.observe(this, nameObserver)

        val numberObserver = Observer<Int>{
            findViewById<TextView>(R.id.mainTV).text = it.toString()
        }
        model.theNumber.observe(this, numberObserver)

        model.loadstuff()

        findViewById<Button>(R.id.mainBTN).setOnClickListener(){
            model.currentName.value = "Bartil"
        }
    }
}