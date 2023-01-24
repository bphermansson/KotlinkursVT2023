package com.paheco.retrofit_simple_api_example.models

import com.google.gson.annotations.SerializedName

data class Support(
    @SerializedName("text")
    var text: String,
    @SerializedName("url")
    var url: String
)