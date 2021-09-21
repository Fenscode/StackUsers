package com.fenscode.stackusers.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getStackUsers() = apiService.getStackUsers("asc","stackoverflow", 1, 50)
}