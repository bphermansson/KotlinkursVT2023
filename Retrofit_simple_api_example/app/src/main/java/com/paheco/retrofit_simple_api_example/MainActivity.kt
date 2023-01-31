// Inspired by https://harshitabambure.medium.com/simple-retrofit-in-android-kotlin-5264b2839645

package com.paheco.retrofit_simple_api_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.paheco.retrofit_simple_api_example.api.ApiInterface
import com.paheco.retrofit_simple_api_example.api.RetrofitClient
import com.paheco.retrofit_simple_api_example.api.SmhiApiInterface
import com.paheco.retrofit_simple_api_example.api.SmhiRetrofitClient
import com.paheco.retrofit_simple_api_example.models.jsonData
import com.paheco.retrofit_simple_api_example.models.smhimaindata

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUserList()
        getSmhiData()
    }
    fun getSmhiData() {
        var txtTemp = findViewById<TextView>(R.id.txtTemp)
        var txtHum = findViewById<TextView>(R.id.txtHumidity)

        val smhiretrofit = SmhiRetrofitClient.getInstance()
        val apiInterface = smhiretrofit.create(SmhiApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getAllweatherData()
                if (response.isSuccessful()) {
                    val json = Gson().toJson(response.body())
                    val gson = Gson()
                    val testModel = gson.fromJson(json, smhimaindata::class.java)

                    if(testModel.toString().isNotEmpty())
                    {
                        var tempmain = testModel.timeSeries[0].parameters[10]
                        var tvalue = tempmain.values[0]
                        var tunit = tempmain.unit
                        println("Temp: " + tvalue + tunit)

                        var hummain = testModel.timeSeries[0].parameters[11]
                        var humvalue = hummain.values[0]
                        var hunit = hummain.unit
                        println("Humidity: " + humvalue + hunit)

                        txtTemp.text = (tvalue.toString() + " " + tunit.toString())
                        txtHum.text = humvalue.toString() + " " + hunit.toString()
                    }
                    else {
                        Toast.makeText(
                            this@MainActivity,
                            "No Data ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } catch (Ex: Exception) {
            Log.e("Error", Ex.localizedMessage.orEmpty())
            }
        }

    }
    fun getUserList() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getAllUsers()
                if (response.isSuccessful()) {
                    val json = Gson().toJson(response.body())
	                val gson = Gson()
                    val testModel = gson.fromJson(json, jsonData::class.java)

                    if (response.body()?.success?.length == 0) {
                        Toast.makeText(
                            this@MainActivity,
                            "No Data ",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        findViewById<TextView>(R.id.txtSuccess).text = testModel.success
                        println(testModel.success)
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage.orEmpty())
            }
        }
    }
}