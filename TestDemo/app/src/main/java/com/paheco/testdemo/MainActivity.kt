package com.paheco.testdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.calcBTN).setOnClickListener{
            var mathHelp = mathFunctions()

            var res = mathHelp.doublerFunction(findViewById<EditText>(R.id.inputTV).text.toString().toInt())
            findViewById<TextView>(R.id.resultTV).text = res.toString()

        }
    }
}
class mathFunctions() {
    fun doublerFunction(numberToDouble : Int) : Int {
        return numberToDouble * 2
    }
}