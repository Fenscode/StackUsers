package com.fenscode.stackusers.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

import com.google.gson.Gson
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient










object RetrofitBuilder {
    private const val BASE_URL = "https://api.stackexchange.com/2.3/"

    var gson = GsonBuilder()
        .setLenient()
        .create()

    var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    var httpClient = OkHttpClient.Builder()
        .addInterceptor(logging)

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}