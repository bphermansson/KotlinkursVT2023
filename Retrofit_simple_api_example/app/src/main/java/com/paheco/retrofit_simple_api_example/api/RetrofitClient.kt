package com.paheco.retrofit_simple_api_example.api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getInstance(): Retrofit {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://reqbin.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
        return retrofit
    }
}