package com.paheco.willitrain

import android.util.Log
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import okio.IOException

class SmhiAPI {
    private val client = OkHttpClient()

    fun loadSmhidata(callback: (Smhidata) -> Unit) {
        var smhiURL = MainActivity.smhiURLP1 + MainActivity.smhiLon + MainActivity.smhiLatTxt + MainActivity.smhiLat + MainActivity.smhiUrlEnding
        Log.i(MainActivity.logTag, smhiURL)

        val req = Request.Builder().url(smhiURL).build()
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
                    callback(smhiData)
                }
            }
        })
    }
}