package com.paheco.retrofit_simple_api_example.models

import com.google.gson.annotations.SerializedName
// This is the main json data: {"success":"true"}

data class smhimaindata(
    @SerializedName("approvedTime")
    var time: String,
    @SerializedName("geometry")
    var referenceTime: String

)
