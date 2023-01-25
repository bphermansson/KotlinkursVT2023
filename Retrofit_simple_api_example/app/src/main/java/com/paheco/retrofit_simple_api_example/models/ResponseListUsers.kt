package com.paheco.retrofit_simple_api_example.models

import com.google.gson.annotations.SerializedName

// Top level json
data class ResponseListUsers(
    @SerializedName("success")
    var success: String,
)