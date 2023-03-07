package com.paheco.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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