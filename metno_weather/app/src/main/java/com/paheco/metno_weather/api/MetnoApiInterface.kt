package com.paheco.willitrain.api

import com.paheco.willitrain.Metnomaindata
import retrofit2.Response
import retrofit2.http.GET

interface MetnoApiInterface {
    @GET("/weatherapi/nowcast/2.0/complete?lat=58.38920&lon=12.75277")
    suspend fun getAllweatherData(): Response<Metnomaindata>
}