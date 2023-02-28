package com.paheco.willitrain.models

import com.google.gson.annotations.SerializedName

data class TimeSeries(
    //@SerializedName("validTime")
    //var validTime: String,
    @SerializedName("parameters")
    var parameters: ArrayList<Parameters>
)

