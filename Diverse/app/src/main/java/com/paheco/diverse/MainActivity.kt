package com.paheco.diverse

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //checkLogin()
        val nameObserver = Observer<String?> {
            val navhostfrag = supportFragmentManager.findFragmentById(R.id.navFragHost) as NavHostFragment
            val nav = navhostfrag.findNavController()

            if(LoginHelper.username.value == null) {
                nav.setGraph(R.navigation.nav_login_graph)
            } else {
                nav.setGraph(R.navigation.nav_graph)
            }
        }

        LoginHelper.username.observe(this, nameObserver)
        LoginHelper.getname(this)

    }
    fun checkLogin(){

        val sharedPref = getSharedPreferences("com.paheco.diverse.sharedprefs", Context.MODE_PRIVATE)
        val user = sharedPref.getString("username", null)

        if (user != null) {
            val navFragHost = findViewById<FragmentContainerView>(R.id.navFragHost)
            val nav = navFragHost.findNavController()
            //nav.setGraph(R.navigation.nav_graph)
        }
        else {
            val navFragHost = findViewById<FragmentContainerView>(R.id.navFragHost)
            val nav = navFragHost.findNavController()
            //nav.setGraph(R.navigation.nav_graph_login2)
        }
    }
}