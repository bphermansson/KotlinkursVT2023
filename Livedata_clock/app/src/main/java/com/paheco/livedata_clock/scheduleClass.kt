package com.paheco.livedata_clock

import java.util.*
import java.util.concurrent.TimeUnit

class ScheduleClass {
        private val timer = Timer()
        fun createTask(task: () -> Unit, intervalSeconds: Long) {
            val millisInterval: Long = TimeUnit.SECONDS.toMillis(intervalSeconds)
            val startDelay: Long = 0

            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    task()
                }
            }, startDelay, millisInterval)
        }
}