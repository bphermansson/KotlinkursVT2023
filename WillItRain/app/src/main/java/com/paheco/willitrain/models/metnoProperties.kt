package com.paheco.willitrain

import com.google.gson.annotations.SerializedName


data class metnoProperties (

  @SerializedName("meta"       ) var meta       : metnoMeta?                 = metnoMeta(),
  @SerializedName("timeseries" ) var timeseries : ArrayList<metnoTimeseries> = arrayListOf()

)