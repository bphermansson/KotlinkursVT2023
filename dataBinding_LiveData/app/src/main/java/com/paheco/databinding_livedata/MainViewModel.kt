package com.paheco.databinding_livedata

import android.content.IntentSender.OnFinished
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import java.util.*

class MainViewModel : ViewModel() {   // En ny klass av typen ViewModel
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val theNumber: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun loadstuff() {
        /*
        Timer().schedule(5000)
         */
        Handler(Looper.getMainLooper()).postDelayed({
            currentName.value = "Hej!"
        }, 5000)

        var startTime = 10000   // In millis
        var timerIntervall = 1000
        theNumber.value = startTime/1000

        val timer = object: CountDownTimer(startTime.toLong(), timerIntervall.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                theNumber.value = theNumber.value!! -1
            }
            override fun onFinish() {
                currentName.value = "IT'S DONE"
            }
        }
        timer.start()
    }


}