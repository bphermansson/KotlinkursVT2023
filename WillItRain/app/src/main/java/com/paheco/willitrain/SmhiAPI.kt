package com.paheco.willitrain

import android.util.Log
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import okio.IOException

class SmhiAPI {
    private val client = OkHttpClient()

    fun loadSmhidata(callback: (Smhidata) -> Unit) {
        val req = Request.Builder().url(MainActivity().smhiURL).build()
        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("willitrainlog", "NOT OK")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    Log.i("willitrainlog", "Response OK")

                    var resString = response.body!!.string()

                    Log.i("willitrainlog", "Response: " + resString)
                    val smhiData = Json{ignoreUnknownKeys = true}.decodeFromString<Smhidata>(resString)
                    Log.i("willitrainlog", "Approved time " + smhiData.approvedTime)
                    callback(smhiData)
                }

            }
        })
    }
/*
    fun loadData() {
        val req = Request.Builder().url("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/12.75277/lat/58.38920/data.json").build()

        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("willitrainlog", "NOT OK")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    var resString = response.body!!.string()

                    Log.i("willitrainlog", resString)
                    doJsonStuff(resString)
                }
            }
        })
    }
    fun doJsonStuff(thejson: String){
        Log.i("willitrainlog", thejson)
        val smhidata = Json{ignoreUnknownKeys = true}.decodeFromString<Smhidata>(thejson)
        Log.i("willitrainlog", smhidata.approvedTime.toString())
        /*
        runOnUiThread(){
            //findViewById<TextView>(R.id.jokeTV).text = thejoke.value
        }

         */


 */
}