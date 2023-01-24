package com.paheco.httprequesttest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit

class StartFragment : Fragment(R.layout.fragment_start)
class DataFragment : Fragment(R.layout.fragment_data)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnNext).setOnClickListener(){
             supportFragmentManager.commit {
              setReorderingAllowed(true)
                 addToBackStack( "tag" )
              add<DataFragment>(R.id.fragmentContainerView)
             }
        }
    }
}


