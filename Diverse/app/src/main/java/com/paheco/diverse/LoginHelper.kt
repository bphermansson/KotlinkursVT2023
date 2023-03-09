package com.paheco.diverse

import android.content.Context
import androidx.lifecycle.MutableLiveData

class LoginHelper {
    companion object {
        var fancytext = "apa"
        val username: MutableLiveData<String?> by lazy {
            MutableLiveData<String?>()
        }
        fun login(context: Context, name: String) {
           val sharedPref = context.getSharedPreferences("com.paheco.diverse.sharedprefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("username", name)
                apply()
            }
            username.value = name

        }

        fun logout(context: Context) {
            val sharedPref = context.getSharedPreferences("com.paheco.diverse.sharedprefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                remove("username")
                apply()
            }
            username.value = null
        }
        fun getname(context: Context) {
            val sharedPref = context.getSharedPreferences("com.paheco.diverse.sharedprefs", Context.MODE_PRIVATE)
            username.value = sharedPref.getString("username", null)
        }
    }

}