package com.paheco.retrofit_simple_api_example.models

import com.google.gson.annotations.SerializedName

// Top level json
/*
page	2
per_page	6
total	12
total_pages	2
data
...
support
url	"https://reqres.in/#support-heading"
text

 */

data class ResponseListUsers(
    @SerializedName("success")
    var success: String,
)

/*
data class ResponseListUsers(
    @SerializedName("data")
    var data: List<Data>,
    @SerializedName("page")
    var page: Int,
    @SerializedName("per_page")
    var perPage: Int,
    @SerializedName("support")
    var support: Support,
    @SerializedName("total")
    var total: Int,
    @SerializedName(
        "total_pages")
    var totalPages: Int
)
*/