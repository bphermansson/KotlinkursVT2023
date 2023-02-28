package com.paheco.willitrain

import com.google.gson.annotations.SerializedName

data class Metnomaindata (

  @SerializedName("type"       ) var type       : String?     = null,
  @SerializedName("geometry"   ) var geometry   : Geometry?   = Geometry(),
  @SerializedName("properties" ) var properties : Properties? = Properties()

)