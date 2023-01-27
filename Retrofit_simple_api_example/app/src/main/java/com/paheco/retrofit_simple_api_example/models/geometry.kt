package com.paheco.retrofit_simple_api_example.models

import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName("type")
    var type: String,
    @SerializedName("coordinates")
    var coordinates: ArrayList<ArrayList<Double>>
)

