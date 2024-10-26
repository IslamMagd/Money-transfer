package com.example.data.repository.utils

import android.util.Log
import com.example.data.remote.response.ErrorResponse
import com.google.gson.Gson

object RepositoryUtils {
    fun parseErrorResponse(errorBody: String): ErrorResponse? {
        return try {
            val gson = Gson()
            gson.fromJson(errorBody, ErrorResponse::class.java)
        } catch (e: Exception) {
            Log.d("trace", "Error parsing error response: ${e.message}")
            null
        }
    }
}