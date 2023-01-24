// Inspired by https://harshitabambure.medium.com/simple-retrofit-in-android-kotlin-5264b2839645

package com.paheco.retrofit_simple_api_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.paheco.retrofit_simple_api_example.api.ApiInterface
import com.paheco.retrofit_simple_api_example.api.RetrofitClient
import com.paheco.retrofit_simple_api_example.models.jsonData
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var txtData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtData = findViewById(R.id.txtData)
        getUserList()
    }

    fun getUserList() {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getAllUsers()
                if (response.isSuccessful()) {
                    var json = Gson().toJson(response.body())
	                val gson = Gson()
                    var testModel = gson.fromJson(json, jsonData::class.java)

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
                Log.e("Error", Ex.localizedMessage)
            }
        }
    }
}