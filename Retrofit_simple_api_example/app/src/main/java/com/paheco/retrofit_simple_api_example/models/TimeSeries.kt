package com.paheco.retrofit_simple_api_example.models

import com.google.gson.annotations.SerializedName

data class TimeSeries(
    @SerializedName("validTime")
    var validTime: String,
    @SerializedName("parameters")
    var parameters: ArrayList<Parameters>
)

