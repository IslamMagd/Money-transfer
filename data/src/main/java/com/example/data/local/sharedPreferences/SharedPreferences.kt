package com.example.data.local.sharedPreferences

interface SharedPreferences {
    fun saveToken(token: String)
    fun getToken(): String?

    fun saveCredentials(email: String, password: String)
    fun getEmail(): String?
    fun getPassword(): String?


}