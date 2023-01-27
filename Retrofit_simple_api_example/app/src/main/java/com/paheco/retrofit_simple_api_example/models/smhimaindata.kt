package com.paheco.retrofit_simple_api_example.models

import com.google.gson.annotations.SerializedName
// This is the main json data: {"success":"true"}



data class smhimaindata(
    @SerializedName("approvedTime")
    var approvedTime: String,
    @SerializedName("referenceTime")
    var referenceTime: String,
    @SerializedName("geometry") // List
    var geometry: Geometry?,       //This is an object
    @SerializedName("timeSeries") // List
    var timeSeries: ArrayList<TimeSeries>,


)
