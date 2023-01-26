package com.paheco.retrofit_simple_api_example.models

import com.google.gson.annotations.SerializedName
// This is the main json data: {"success":"true"}

data class jsonData2(
    @SerializedName("success")
    var success: String
)
