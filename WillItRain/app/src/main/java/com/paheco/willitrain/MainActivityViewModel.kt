package com.paheco.willitrain

import android.util.Log
import androidx.lifecycle.ViewModel
import java.time.LocalTime

class MainActivityViewModel : ViewModel() {
    // If this is a shared viewmodel, we could store values here

    fun getSmhiTemp( receivedData : Smhidata, callback: (String) -> Unit) {
        var smhitemp = removeFixes(receivedData.timeSeries[0].parameters[10].values.toString())
        Log.i("willitrainlog", "Temp: $smhitemp" )
        callback(smhitemp.plus("C"))
    }

    fun getSmhiHum( receivedData : Smhidata, callback: (String) -> Unit) {
        var smhiHum = removeFixes(receivedData.timeSeries[0].parameters[15].values.toString()).plus("%")
        Log.i("willitrainlog", "Humidity: $smhiHum" )
        callback(smhiHum)
    }

    fun getSmhiPressure( receivedData : Smhidata, callback: (String) -> Unit) {
        var smhiPressure = receivedData.timeSeries[0].parameters[11].values[0].toString().plus("hPa")
        Log.i("willitrainlog", "Air pressure: $smhiPressure" )
        callback (smhiPressure)
    }

    fun getSmhiWindspeed( receivedData : Smhidata, callback: (String) -> Unit) {
        var smhiWindspeed = receivedData.timeSeries[0].parameters[14].values[0].toString().plus("m/S")
        Log.i("willitrainlog", "Wind speed: $smhiWindspeed" )
        callback(smhiWindspeed)
    }
    fun getSmhiWinddir( receivedData : Smhidata, callback: (String) -> Unit) {
        var smhiWinddir = receivedData.timeSeries[0].parameters[13]
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
        Log.i("willitrainlog", "Dir: $dirAsText")
        callback(dirAsText)
    }

    fun getSmhiWeathersymbol( receivedData : Smhidata, callback: (String) -> Unit) {
        var smhiWeathersymbol = receivedData.timeSeries[0].parameters[18]
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
        var wSymbol = wType[smhiWeathersymbol.values[0].toInt()]
        Log.i("willitrainlog", "Weather type: $wSymbol")
        //binding.smhiWeathersymbol.text = wSymbol
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

