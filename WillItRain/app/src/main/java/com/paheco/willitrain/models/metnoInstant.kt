package com.paheco.willitrain

import com.google.gson.annotations.SerializedName


data class metnoInstant (

  @SerializedName("details" ) var metnoDetails : metnoDetails? = metnoDetails()

)