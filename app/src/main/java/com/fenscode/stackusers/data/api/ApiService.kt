package com.fenscode.stackusers.data.api

import com.fenscode.stackusers.data.model.Users
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList

interface ApiService {

    @GET("users/moderators")
    suspend  fun getStackUsers(
        @Query("order") order: String,
        @Query("site") site: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Users
}