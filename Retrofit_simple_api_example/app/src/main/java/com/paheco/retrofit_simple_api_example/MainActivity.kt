// Inspired by https://harshitabambure.medium.com/simple-retrofit-in-android-kotlin-5264b2839645

package com.paheco.retrofit_simple_api_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    lateinit var txtData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtData = findViewById(R.id.txtData)
        //getUserList()
        getSmhiData()
    }
    fun getSmhiData() {
        val smhiretrofit = SmhiRetrofitClient.getInstance()
        val apiInterface = smhiretrofit.create(SmhiApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getAllweatherData()
                if (response.isSuccessful()) {
                    val json = Gson().toJson(response.body())
                    val gson = Gson()
                    val testModel = gson.fromJson(json, smhimaindata::class.java)

                    var tempmain = testModel.timeSeries[0].parameters[10]
                    var tvalue = tempmain.values[0]
                    var tunit = tempmain.unit
                    println("Temp: " + tvalue + tunit)

                    var hummain = testModel.timeSeries[0].parameters[11]
                    var humvalue = hummain.values[0]
                    var hunit = hummain.unit
                    println("Humidity: " + humvalue + hunit)
/*
                    if (response.body()?.referenceTime?.length == 0) {
                        Toast.makeText(
                            this@MainActivity,
                            "No Data ",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        //txtData.text = testModel.approvedTime
                        //println(testModel.approvedTime)
                    }

 */
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
                        txtData.text = testModel.success
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