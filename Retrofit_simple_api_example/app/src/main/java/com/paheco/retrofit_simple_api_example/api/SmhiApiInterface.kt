package com.paheco.retrofit_simple_api_example.api

import com.paheco.retrofit_simple_api_example.models.jsonData
import retrofit2.Response
import retrofit2.http.GET

interface SmhiApiInterface {
    @GET("/api/category/pmp3g/version/2/geotype/point/lon/12.75277/lat/58.38920/data.json")
    suspend fun getAllUsers(): Response<jsonData>
}