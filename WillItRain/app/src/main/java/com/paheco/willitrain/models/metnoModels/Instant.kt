package com.paheco.willitrain

import com.google.gson.annotations.SerializedName


data class Instant (

  @SerializedName("details" ) var details : Details? = Details()

)