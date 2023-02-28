package com.paheco.willitrain.models

import com.google.gson.annotations.SerializedName

data class Smhimaindata(
    //@SerializedName("approvedTime")
    //var approvedTime: String,
    //@SerializedName("referenceTime")
    //var referenceTime: String,
    //@SerializedName("Geometry.kt")
    //var geometry: Geometry?,
    @SerializedName("timeSeries")
    var timeSeries: ArrayList<TimeSeries>,
)
