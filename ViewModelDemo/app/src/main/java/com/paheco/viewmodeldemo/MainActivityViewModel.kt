package com.paheco.viewmodeldemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {
    var number = 0
    fun addOne() {
        number++
    }
}
