package com.paheco.willitrain.api

import android.util.Log
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import okio.IOException

class smhiOkhttpApi {
    private val client = OkHttpClient()
    fun loadSmhidata(){
        //             .baseUrl("https://opendata-download-metfcst.smhi.se")

        //     @GET("/api/category/pmp3g/version/2/geotype/point/lon/12.75277/lat/58.38920/data.json")
        /*
        val smhiurl = ("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/12.75277/lat/58.38920/data.json")

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

         */
    }
}