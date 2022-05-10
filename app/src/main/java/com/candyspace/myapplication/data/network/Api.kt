package com.candyspace.myapplication.data.network

import com.candyspace.myapplication.models.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {
    @GET("users?site=stackoverflow")
    suspend fun getUsers(): Response<User>

    companion object {
        private val BASEURL = "https://api.stackexchange.com/2.3/"
        operator fun invoke(): Api {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASEURL)
                .build()
                .create(Api::class.java)
        }
    }
}