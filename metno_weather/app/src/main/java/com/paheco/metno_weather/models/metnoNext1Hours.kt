package com.paheco.willitrain

import com.google.gson.annotations.SerializedName


data class metnoNext1Hours (

  @SerializedName("summary" ) var metnoSummary : metnoSummary? = metnoSummary(),
  @SerializedName("details" ) var metnoDetails : metnoDetails? = metnoDetails()

)