package com.paheco.retrofit_simple_api_example.models

import com.google.gson.annotations.SerializedName
// This is the main json data: {"success":"true"}

data class smhidata(
    @SerializedName("validTime")
    var validTime: String,
    @SerializedName("parameters")
    var parameters: List<Smhiparams>
)

data class Smhiparams(
    @SerializedName("name")
    var name: String,
    @SerializedName("levelType")
    var levelType: String,
    @SerializedName("level")
    var level: String,
    @SerializedName("unit")
    var unit: String,
    @SerializedName("values")
    var values: List<Vals>, // This seems to be correct

)

data class  Vals(
    @SerializedName("0")
    var values2: String,
)

// E/Error: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was NUMBER at line 1 column 279 path $.timeSeries[0].parameters[0].values[0]

