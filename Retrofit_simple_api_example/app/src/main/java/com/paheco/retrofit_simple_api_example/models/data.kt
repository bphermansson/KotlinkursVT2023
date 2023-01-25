package com.paheco.retrofit_simple_api_example.models

import com.google.gson.annotations.SerializedName
// This is the main json data
data class jsonData(
    @SerializedName("success")
    var success: String
)
