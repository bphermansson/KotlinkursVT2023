package com.paheco.willitrain.models

import com.google.gson.annotations.SerializedName

data class Geometry(
    //@SerializedName("type")
    //var types: String,
    @SerializedName("coordinates")
    var coordlinates: ArrayList<ArrayList<Double>>,
)

