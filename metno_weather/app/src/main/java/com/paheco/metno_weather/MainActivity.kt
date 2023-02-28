package com.paheco.metno_weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.paheco.willitrain.Metnomaindata
import com.paheco.willitrain.api.MetnoApiInterface
import com.paheco.willitrain.api.MetnoRetrofitClient
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMetnoData()

    }

    fun getMetnoData() {
        val metnoretrofit = MetnoRetrofitClient.getInstance()
        val metnoApiInterface = metnoretrofit.create(MetnoApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = metnoApiInterface.getAllweatherData()
                if (response.isSuccessful()) {
                    Log.i("willitrainlog", "Got Met.no data")
                    val json = Gson().toJson(response.body())
                    val receivedData = Gson().fromJson(json, Metnomaindata::class.java)

                    // Met.no temperature and more
                    var metnoTime = receivedData.metnoProperties!!.timeseries[0].time
                    var metnoTemp = receivedData.metnoProperties!!.timeseries[0].metnoData?.metnoInstant?.metnoDetails?.airTemperature
                    var metnoHum = receivedData.metnoProperties!!.timeseries[0].metnoData?.metnoInstant?.metnoDetails?.relativeHumidity
                    var metnoWindspeed = receivedData.metnoProperties!!.timeseries[0].metnoData?.metnoInstant?.metnoDetails?.windSpeed
                    var metnoWindDir = receivedData.metnoProperties!!.timeseries[0].metnoData?.metnoInstant?.metnoDetails?.windFromDirection

                    var metnoSymbol = receivedData.metnoProperties!!.timeseries[0].metnoData?.metnoNext1Hours?.metnoSummary?.symbolCode


                    Log.i("willitrainlog", metnoTime.toString())

                    Log.i("willitrainlog", metnoTemp.toString() + "-" + metnoHum.toString())
                    Log.i("willitrainlog", metnoWindspeed.toString() + "-" + metnoWindDir.toString())
                    Log.i("willitrainlog", metnoSymbol.toString())

                    //var cleantsvalue = removeFixes(tempmain.values.toString())
                    //binding.metnoTempTV.text = cleantsvalue.plus("C")

                    // Met.no Humidity
                    //var hummain = receivedData.timeSeries[0].parameters[15]
                    //var hsvalue = removeFixes(hummain.values.toString())
                    //binding.metnoHumTV.text = hsvalue.plus("%")
                }
                else {
                    // TODO: What if something goes wrong?
                    Log.i("willitrainlog", "Get Met.no, error! ")
                }
            } catch (Ex: Exception) {
                Log.i("willitrainlog", Ex.localizedMessage.orEmpty())
            }
        }
    }

}