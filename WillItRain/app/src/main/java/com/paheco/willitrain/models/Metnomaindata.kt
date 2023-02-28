package com.paheco.willitrain

import com.google.gson.annotations.SerializedName


data class Metnomaindata (

  @SerializedName("type"       ) var type       : String?     = null,
  @SerializedName("geometry"   ) var metnoGeometry   : metnoGeometry?   = metnoGeometry(),
  @SerializedName("properties" ) var metnoProperties : metnoProperties? = metnoProperties()

)