package com.paheco.retrofit_simple_api_example.api

import com.paheco.retrofit_simple_api_example.models.ResponseListUsers
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/echo/get/json")
    suspend fun getAllUsers(): Response<ResponseListUsers>
}