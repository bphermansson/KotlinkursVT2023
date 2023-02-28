package com.paheco.willitrain

import com.google.gson.annotations.SerializedName


data class Details (

  @SerializedName("precipitation_amount" ) var precipitationAmount : Int? = null

)