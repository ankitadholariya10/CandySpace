package com.candyspace.myapplication.data.repositories

import com.candyspace.myapplication.data.network.Api

class NetworkRepository(
    private val api: Api
) : SafeApiRequest() {

    suspend fun getUser() = apiRequest { api.getUsers() }
}