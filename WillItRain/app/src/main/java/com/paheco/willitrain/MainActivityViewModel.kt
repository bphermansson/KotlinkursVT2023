package com.paheco.willitrain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {
    var number = 8
    fun addOne() {
        number++
    }
    fun getSmhiData(){

    }
}
