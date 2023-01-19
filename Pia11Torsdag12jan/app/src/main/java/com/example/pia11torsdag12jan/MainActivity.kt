package com.example.pia11torsdag12jan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var usernameET : EditText  // Deklarera usernameET, men vänta på vilket typ det är
    lateinit var passwordET : EditText
    var currentsum=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameET=findViewById(R.id.loginUsernameEditText) // Hämta usernameET. Typen behöver inte anges(redan angiven ovan).
        passwordET=findViewById(R.id.loginPasswordEditText)

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val username = usernameET.toString()
            var password = passwordET.toString()
            if(username == "" || password == ""){
                findViewById<TextView>(R.id.LoginErrorTextView).visibility = View.VISIBLE
            }
            else
            {
                findViewById<TextView>(R.id.LoginErrorTextView).visibility = View.GONE
            }

        }
        findViewById<TextView>(R.id.loginHeaderText).setOnClickListener {
            findViewById<TextView>(R.id.loginHeaderText).text="123"
        }
        findViewById<Button>(R.id.addBT).setOnClickListener {
                var addET = findViewById<EditText>(R.id.valueET)
                var addString = addET.text.toString()

                addString.toIntOrNull()?.let {
                    currentsum = currentsum + it
                }
                findViewById<TextView>(R.id.sumText).text = currentsum.toString()

            // Handle null values
            var a: String = "abc" // Initiera och definiera en string. String är inte nullable:
            //a = null // compilation error

            var b: String? = "abc" // can be set to null
            b = null
            b?.let { println("b is null!: " + it) } // .let means just do it if b aint null
            b = "321"
            b?.let { println("b aint null!: " + it) } // .let means just do it if b aint null

            val l = a.length
            //val ll = b.length   // Unsafe
            var ll = b?.length
            b = "123"
            ll = b?.length
            println(ll)

            a?.let { println("a: " + it) } // prints Kotlin and ignores null


        }
    }
}