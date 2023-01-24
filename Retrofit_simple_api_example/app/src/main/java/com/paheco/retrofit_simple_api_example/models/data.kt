package com.paheco.retrofit_simple_api_example.models

import com.google.gson.annotations.SerializedName
// This is the main json data
/*
data
0
id	7
email	"michael.lawson@reqres.in"
first_name	"Michael"
last_name	"Lawson"
avatar	"https://reqres.in/img/faces/7-image.jpg"
 */

data class jsonData(
    @SerializedName("success")
    var success: String,
/*    @SerializedName("email")
    var email: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("last_name")
    var lastName: String,
 */
)
