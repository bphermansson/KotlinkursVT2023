package com.paheco.willitrain

import com.google.gson.annotations.SerializedName


data class metnoGeometry (

  @SerializedName("type"        ) var type        : String?           = null,
  @SerializedName("coordinates" ) var coordinates : ArrayList<Double> = arrayListOf()

)