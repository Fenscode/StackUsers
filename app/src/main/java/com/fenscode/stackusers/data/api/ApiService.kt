package com.fenscode.stackusers.data.api

import com.fenscode.stackusers.data.model.Users
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getStackUsers(): List<Users>
}