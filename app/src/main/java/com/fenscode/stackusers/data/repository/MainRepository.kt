package com.fenscode.stackusers.data.repository

import com.fenscode.stackusers.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getStackUsers() = apiHelper.getStackUsers()
}