package com.paheco.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var db : AppDatabase

    var peopleAdapter = PeopleAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        val peoplerv = findViewById<RecyclerView>(R.id.peopleRV)
        peoplerv.adapter = peopleAdapter
        peoplerv.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.addBTN).setOnClickListener{
            val firstname = findViewById<EditText>(R.id.firstnameET).text.toString()
            val lastname = findViewById<EditText>(R.id.lastnameET).text.toString()

            val nyperson = User (0, firstname, lastname)

            CoroutineScope(Dispatchers.IO).launch { // Run in another thread
                val userDao = db.userDao()
                userDao.insertAll(nyperson)
                loadpeople()
            }
        }

        loadpeople()
    }

    fun clickRow(person: User) {
        CoroutineScope(Dispatchers.IO).launch { // Run in another thread
            val userDao = db.userDao()
            userDao.delete(person)
            loadpeople()
        }
    }

    fun loadpeople() {

        CoroutineScope(Dispatchers.IO).launch { // Run in another thread
            val userDao = db.userDao()
            val allpeople = userDao.getAll()
            peopleAdapter.people = allpeople.toMutableList()
            runOnUiThread() {
                peopleAdapter.notifyDataSetChanged()
            }
        }
    }

    fun exempelroom() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        CoroutineScope(Dispatchers.IO).launch { // Run in another thread
            val userDao = db.userDao()

            //var nyperson  = User(0, "Rob", "")
            //userDao.insertAll(nyperson)

            var users: List<User> = userDao.getAll()
            Log.i("pia11", "ANTAL: " + users.size.toString())
            for (currentuser in users) {
                Log.i("pia11", currentuser.uid.toString())
                Log.i("pia11", currentuser.firstName!!)
            }

            users = userDao.getNoLastname()
            Log.i("pia11", "ANTAL: " + users.size.toString())
            for (currentuser in users) {
                Log.i("pia11", currentuser.uid.toString())
                Log.i("pia11", currentuser.firstName!!)
            }
        }
    }
}