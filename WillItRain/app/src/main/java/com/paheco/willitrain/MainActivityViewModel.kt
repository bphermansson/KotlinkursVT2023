package com.paheco.willitrain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalTime

class MainActivityViewModel : ViewModel() {
    val tempValue: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val humidityValue: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val windSpeedValue: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val windDirectionValue: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val airPressureValue: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val weatherTypeValue: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    var timeValue = MutableLiveData<String>()

    fun fetchSmhiWeather(hoursAhead : Int) {
        // Get data and update UI
        SmhiAPI().loadSmhidata() { smhidata ->
            getSmhiTemp(smhidata, hoursAhead) { smhitemp -> Unit
                tempValue.postValue(smhitemp)
            }
            getSmhiHum(smhidata, hoursAhead) { smhihum ->
                humidityValue.postValue(smhihum)
            }
            getSmhiPressure(smhidata, hoursAhead) { smhipres ->

                airPressureValue.postValue(smhipres)
            }
            getSmhiWindspeed(smhidata, hoursAhead) { smhiws ->

                windSpeedValue.postValue(smhiws)
            }
            getSmhiWinddir(smhidata, hoursAhead) { smhiwd ->
                windDirectionValue.postValue(smhiwd)
            }
            getSmhiWeathersymbol(smhidata, hoursAhead) { smhiwsym ->
                weatherTypeValue.postValue(smhiwsym)
            }
            getLocalTime() { ltime ->
                timeValue.postValue(ltime)
            }
        }
        }

    fun getSmhiTemp( receivedData : Smhidata, hoursAhead : Int, callback: (String) -> Unit) {
        val smhitemp = removeFixes(receivedData.timeSeries[hoursAhead].parameters[10].values.toString())
        Log.i("willitrainlog", "Temp: $smhitemp" )
        callback(smhitemp.plus("C"))
    }

    fun getSmhiHum( receivedData : Smhidata, hoursAhead : Int, callback: (String) -> Unit) {
        val smhiHum = removeFixes(receivedData.timeSeries[hoursAhead].parameters[15].values.toString()).plus("%")
        Log.i("willitrainlog", "Humidity: $smhiHum" )
        callback(smhiHum)
    }

    fun getSmhiPressure( receivedData : Smhidata, hoursAhead : Int, callback: (String) -> Unit) {
        val smhiPressure = receivedData.timeSeries[hoursAhead].parameters[11].values[0].toString().plus("hPa")
        Log.i("willitrainlog", "Air pressure: $smhiPressure" )
        callback (smhiPressure)
    }

    fun getSmhiWindspeed( receivedData : Smhidata, hoursAhead : Int, callback: (String) -> Unit) {
        val wsnative = receivedData.timeSeries[hoursAhead].parameters[14].values[0]
        // Sometimes this value is below zero, which is incorrect.
        var smhiWindspeed = ""
        if (wsnative < 0) {
            smhiWindspeed = "N/A"
        }
        else {
            smhiWindspeed = receivedData.timeSeries[hoursAhead].parameters[14].values[0].toString().plus("m/S")
        }
        Log.i("willitrainlog", "Wind speed: $smhiWindspeed" )
        callback(smhiWindspeed)
    }
    fun getSmhiWinddir( receivedData : Smhidata, hoursAhead : Int, callback: (String) -> Unit) {
        val smhiWinddir = receivedData.timeSeries[hoursAhead].parameters[13]

        // Convert wind direction number to a direction
        val direction = listOf(
            "N",
            "NNE",
            "NE",
            "ENE",
            "E",
            "ESE",
            "SE",
            "SSE",
            "S",
            "SSW",
            "SW",
            "WSW",
            "W",
            "WNW",
            "NW",
            "NNW",
            "N"
        )

        val dirAsText = direction[((smhiWinddir.values[0] + 11.25) / 22.5).toInt()]

        //val dirAsText = direction[smhiWinddir.values]
        Log.i("willitrainlog", "Dir: $dirAsText")
        Log.i("willitrainlog", "Dir: " + smhiWinddir.values[0])
        callback(dirAsText)
    }

    fun getSmhiWeathersymbol( receivedData : Smhidata, hoursAhead : Int, callback: (String) -> Unit) {
        val smhiWeathersymbol = receivedData.timeSeries[hoursAhead].parameters[18]
        val wType = arrayOf(
            "Clear sky",
            "Nearly clear sky",
            "Variable cloudiness",
            "Halfclear sky",
            "Cloudy sky",
            "Overcast",
            "Fog",
            "Light rain showers",
            "Moderate rain showers",
            "Heavy rain showers",
            "Thunderstorm",
            "Light sleet showers",
            "Moderate sleet showers",
            "Heavy sleet showers",
            "Light snow showers",
            "Moderate snow showers",
            "Heavy snow showers",
            "Light rain",
            "Moderate rain",
            "Heavy rain",
            "Thunder",
            "Light sleet",
            "Moderate sleet",
            "Heavy sleet",
            "Light snowfall",
            "Moderate snowfall",
            "Heavy snowfall"
        )
        val wSymbol = wType[smhiWeathersymbol.values[0].toInt()]
        Log.i("willitrainlog", "Weather type: $wSymbol")
        // TODO: Get weather symbols to show instead of text
        callback(wSymbol)
    }
    fun getLocalTime(callback: (String) -> Unit){
        val lTime = LocalTime.now().toString().substringBefore(".")
        Log.i("willitrainlog", "Local time: $lTime" )
        callback(lTime)
    }
    fun removeFixes(string : String) : String{
        return string.removePrefix("[").removeSuffix("]")
    }
}

