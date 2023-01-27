package com.paheco.willitrain.api

import com.paheco.willitrain.models.Smhimaindata
import retrofit2.Response
import retrofit2.http.GET

interface SmhiApiInterface {
    @GET("/api/category/pmp3g/version/2/geotype/point/lon/12.75277/lat/58.38920/data.json")
    suspend fun getAllweatherData(): Response<Smhimaindata>
}