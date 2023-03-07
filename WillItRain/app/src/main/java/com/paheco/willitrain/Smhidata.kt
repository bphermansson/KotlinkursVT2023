package com.paheco.willitrain

import kotlinx.serialization.Serializable

@Serializable
data class Smhidata (val approvedTime : String?, val referenceTime : String?, val geometry : Geometry?, val timeSeries: ArrayList<TimeSeries> )

@Serializable
data class Geometry (
    var type : String?,
    var coordinates : ArrayList<ArrayList<Double>>
)

@Serializable
data class TimeSeries(
    var validTime: String?,
    var parameters:  ArrayList<Parameters>
)

@Serializable
data class Parameters(
    var name: String?,
    var levelType: String?,
    var level: Int?,
    var unit: String?,
    var values: ArrayList<Float>
)