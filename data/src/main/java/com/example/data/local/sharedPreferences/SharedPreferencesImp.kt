package com.example.data.local.sharedPreferences

import android.content.Context
import javax.inject.Inject

class SharedPreferencesImp @Inject constructor(
    private val context: Context
) : SharedPreferences {

    private val sharedPreferences =
        context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    override fun saveToken(token: String) {
        with(sharedPreferences.edit()) {
            putString("token", token)
            apply()
        }
    }

    override fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    override fun saveCredentials(email: String, password: String) {
        with(sharedPreferences.edit()) {
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    override fun getEmail(): String? {
        return sharedPreferences.getString("email", "")
    }

    override fun getPassword(): String? {
        return sharedPreferences.getString("password", "")
    }
}