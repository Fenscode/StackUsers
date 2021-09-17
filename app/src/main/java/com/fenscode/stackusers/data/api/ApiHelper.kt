package com.fenscode.stackusers.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getStackUsers() = apiService.getStackUsers()
}