package com.paheco.willitrain

import com.google.gson.annotations.SerializedName


data class metnoMeta (

  @SerializedName("updated_at"     ) var updatedAt     : String? = null,
  @SerializedName("units"          ) var metnoUnits         : metnoUnits?  = metnoUnits(),
  @SerializedName("radar_coverage" ) var radarCoverage : String? = null

)