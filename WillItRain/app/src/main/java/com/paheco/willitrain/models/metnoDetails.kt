package com.paheco.willitrain

import com.google.gson.annotations.SerializedName


data class metnoDetails (

  @SerializedName("precipitation_amount" ) var precipitationAmount : Int? = null,
  @SerializedName("air_temperature" ) var airTemperature : Float? = null,
  @SerializedName("relative_humidity" ) var relativeHumidity : Float? = null,
  @SerializedName("wind_speed" ) var windSpeed : Float? = null,
  @SerializedName("wind_from_direction" ) var windFromDirection : Float? = null
)