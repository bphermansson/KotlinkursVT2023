package com.paheco.willitrain

import com.google.gson.annotations.SerializedName


data class metnoTimeseries (

  @SerializedName("time" ) var time : String? = null,
  @SerializedName("data" ) var metnoData : metnoData?   = metnoData()

)