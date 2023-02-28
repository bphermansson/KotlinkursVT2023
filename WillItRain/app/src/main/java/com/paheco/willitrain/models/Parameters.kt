package com.paheco.willitrain.models

import com.google.gson.annotations.SerializedName

data class Parameters(
    //@SerializedName("name")
    //var name: String,
    //@SerializedName("levelType")
    //var levelType: String,
    //@SerializedName("level")
    //var level: Int,
    //@SerializedName("unit")
    //var unit: String,
    @SerializedName("values")
    var values: ArrayList<Float>
)

