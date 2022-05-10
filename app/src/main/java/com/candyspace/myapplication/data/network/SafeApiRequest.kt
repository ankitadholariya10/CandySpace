package com.candyspace.myapplication.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = withContext(Dispatchers.IO) {
            call.invoke()
        }
        if (response.isSuccessful) {
            Log.e("isSuccessful",response.body().toString())
            return response.body()!!
        } else {
            //TODO: API ERROR EXCEPTION
                Log.e("Exception",response.message())
            throw ApiException(response.code().toString())
        }
    }
}

class ApiException(message: String) : IOException(message)