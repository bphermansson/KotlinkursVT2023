package com.paheco.livedata_clock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    private val model by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val timeObserver = Observer<String>{ newTime ->
            findViewById<TextView>(R.id.timeTV).text = newTime
        }
        model.currentTime.observe(this, timeObserver)
    }

    override fun onResume() {
        val sc = ScheduleClass()
        sc.createTask(
            { showTime() },
            1
        )
        super.onResume()
    }
    fun showTime() {
        val tInt = LocalTime.now().toString().substringBefore(".")
        model.currentTime.postValue(tInt)
    }

    class MainViewModel : ViewModel() {   // En ny klass av typen ViewModel
        val currentTime: MutableLiveData<String> by lazy {
            MutableLiveData<String>()
        }
    }
}

