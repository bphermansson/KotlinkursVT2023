package com.paheco.api

import android.util.Log
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import okio.IOException

class ChuckAPI {
    private val client = OkHttpClient()

    fun loadJoke(callback: (Chuckjoke) -> Unit) {
        val req = Request.Builder().url("https://api.chucknorris.io/jokes/random").build()
        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("chuckapi", "NOT OK")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    var resString = response.body!!.string()

                    Log.i("chuckapi", resString)
                    val thejoke = Json{ignoreUnknownKeys = true}.decodeFromString<Chuckjoke>(resString)
                    Log.i("chuckapi", thejoke.value)
                    callback(thejoke)
                }

            }
        })
    }

    fun loadData() {

        // This cant add options to the request, not good.
        // https://api.chucknorris.io/jokes/random
        /*
        var jokeurl = URL("https://api.chucknorris.io/jokes/random")
        var chuckjoke = jokeurl.readText()
        Log.i("chuckapi", chuckjoke)
         */

        val req = Request.Builder().url("https://api.chucknorris.io/jokes/random").build()

        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("chuckapi", "NOT OK")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    var resString = response.body!!.string()

                    Log.i("chuckapi", resString)
                    doJsonStuff(resString)
                }
            }
        })

        /*
        // This is run on the mainthread - not ok
        client.newCall(req).execute().use { response ->
            if(response.isSuccessful){
                Log.i("chuckapi", "OK")
                Log.i("chuckapi", response.body!!.string())
            }
            else{
                Log.i("chuckapi", "NOT OK")
            }
        }
         */
    }
    fun doJsonStuff(thejson: String){
        Log.i("chuckapi", thejson)
        //val moshi = Moshi.Builder().build()
        //val jsonAdapter : JsonAdapter<Chuckjoke> = moshi.adapter<Chuckjoke)
        //val thejoke = json

        val thejoke = Json{ignoreUnknownKeys = true}.decodeFromString<Chuckjoke>(thejson)
        Log.i("chuckapi", thejoke.value)
        /*
        runOnUiThread(){
            //findViewById<TextView>(R.id.jokeTV).text = thejoke.value
        }

         */
    }
}