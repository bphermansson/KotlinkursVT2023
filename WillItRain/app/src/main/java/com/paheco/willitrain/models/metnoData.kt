package com.paheco.willitrain

import com.google.gson.annotations.SerializedName


data class metnoData (

  @SerializedName("instant"      ) var metnoInstant    : metnoInstant?    = metnoInstant(),
  @SerializedName("next_1_hours" ) var metnoNext1Hours : metnoNext1Hours? = metnoNext1Hours()

)